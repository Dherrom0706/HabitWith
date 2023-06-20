package www.iesmurgi.habitwith.ui.fragments.principal.recetas

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import www.iesmurgi.habitwith.adapters.RecetasAdapter
import www.iesmurgi.habitwith.databinding.FragmentDietaBinding
import www.iesmurgi.habitwith.models.APIService
import www.iesmurgi.habitwith.models.RecetaHit
import www.iesmurgi.habitwith.models.RecetaResponse
import www.iesmurgi.habitwith.models.onItemClickListener
import www.iesmurgi.habitwith.ui.fragments.login.LoginFragment

/**
 * Fragmento que muestra una lista de recetas de acuerdo a la dieta del usuario.
 */
class DietaFragment : Fragment() {

    private val BASE_URL: String = "https://api.edamam.com/api/recipes/v2/"
    private var app_id = "b33a89ee"
    private var app_key = "b9e86f29803194fe3afc0ecf975a7861"
    private lateinit var adapter: RecetasAdapter
    private lateinit var binding: FragmentDietaBinding
    private var recipes: MutableList<RecetaHit> = mutableListOf()
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var dieta: String = ""
    private var siguiente: String = ""
    private var isLoadingData = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDietaBinding.inflate(inflater, container, false)
        obtenerTipoDietaUsuario()
        initRecyclerView(recipes)
        return binding.root
    }

    /**
     * Obtiene el tipo de dieta del usuario desde Firestore y realiza la búsqueda de recetas.
     */
    private fun obtenerTipoDietaUsuario() {
        val userId = firebaseAuth.currentUser?.uid
        if (userId != null) {
            val userDocRef = firestore.collection("usuarios").document(userId)
            userDocRef.get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        dieta = document.getString("objetivo").toString()
                        performSearchRecipes()
                    } else {
                        // No se encontró el documento del usuario
                    }
                }
                .addOnFailureListener { exception ->
                    // Ocurrió un error al obtener el tipo de dieta del usuario
                    if (exception is FirebaseFirestoreException) {
                        // Handle Firestore exceptions
                    }
                }
        } else {
            // No hay un usuario logeado
        }
    }

    /**
     * Inicializa el RecyclerView y el adaptador para mostrar la lista de recetas.
     *
     * @param recetas La lista de recetas a mostrar.
     */
    private fun initRecyclerView(recetas: MutableList<RecetaHit>) {
        adapter = RecetasAdapter(recetas)
        binding.rvRecetas.layoutManager = LinearLayoutManager(context)
        binding.rvRecetas.adapter = adapter
        adapter.setOnItemClickListener(object : onItemClickListener {
            override fun onItemClick(position: Int) {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(recipes[position].recipe.shareAs))
                startActivity(browserIntent)
            }
        })

        binding.rvRecetas.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                // Verificar si se ha llegado al final del RecyclerView - 3 posiciones para que empiece a cargar antes
                if (!isLoadingData && lastVisibleItem >= totalItemCount - 3) {
                    // Llegó al lugar donde toca, carga de datos adicionales
                    loadMoreData()
                }
            }
        })
    }

    /**
     * Crea una instancia de Retrofit con la configuración necesaria.
     *
     * @return La instancia de Retrofit.
     */
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Realiza la búsqueda inicial de recetas de acuerdo a la dieta del usuario.
     */
    private fun performSearchRecipes() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val recipesResponse = searchRecipes("?type=public&app_id=$app_id&app_key=$app_key&diet=$dieta&mealType=" +
                        "Breakfast&mealType=Dinner&mealType=Lunch&dishType=Main%20course")
                recipes.clear()
                recipes.addAll(recipesResponse.hits)
                siguiente = recipesResponse._links.next.href.replace(BASE_URL, "") // Obtener el enlace siguiente
                adapter.notifyDataSetChanged()
            } catch (e: Exception) {
                Log.e("MUSCULO", "Error: ${e.message}")
            }
        }
    }

    /**
     * Carga más datos de recetas cuando se ha llegado al final del RecyclerView.
     */
    private fun loadMoreData() {
        if (!siguiente.isNullOrEmpty()) {
            isLoadingData = true // Establecer el indicador de carga a true

            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val recipesResponse = searchRecipes(siguiente)
                    recipes.addAll(recipesResponse.hits)
                    adapter.notifyDataSetChanged()
                    siguiente = recipesResponse._links.next.href
                } catch (e: Exception) {
                    Log.e("Receta", "Error: ${e.message}")
                } finally {
                    isLoadingData = false // Establecer el indicador de carga a false después de completar la carga
                }
            }
        }
    }

    /**
     * Realiza la búsqueda de recetas utilizando Retrofit y devuelve el resultado.
     *
     * @param enlace El enlace de búsqueda de recetas.
     * @return La respuesta de la búsqueda de recetas.
     */
    private suspend fun searchRecipes(enlace: String): RecetaResponse {
        Log.d("Dieta1", dieta)
        val call = getRetrofit().create(APIService::class.java)
            .getRecipes(enlace)

        Log.d("siguiente", siguiente)
        if (call.isSuccessful) {
            val response = call.body() ?: throw Exception("Cuerpo de la respuesta vacío")
            siguiente = response._links.next.href.replace(BASE_URL, "")
            return response
        } else {
            throw Exception("No se encontraron recetas. Code: ${call.code()}")
        }
    }
}