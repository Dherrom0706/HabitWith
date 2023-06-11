package www.iesmurgi.habitwith.ui.fragments.principal.ejercicios

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import www.iesmurgi.habitwith.adapters.EjercicioAdapter
import www.iesmurgi.habitwith.databinding.ActivityEjerciciosBinding
import www.iesmurgi.habitwith.models.APIService
import www.iesmurgi.habitwith.models.Ejercicio
import www.iesmurgi.habitwith.models.onItemClickListener

/**
 * Actividad que muestra una lista de ejercicios relacionados con un músculo específico.
 */
class EjerciciosActivity : AppCompatActivity() {

    private val BASE_URL : String = "https://wger.de/api/v2/"
    private lateinit var binding: ActivityEjerciciosBinding
    private lateinit var adapter: EjercicioAdapter
    private val exercises = mutableListOf<Ejercicio>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjerciciosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()

        intent.getStringExtra("MUSCULO")?.let { performSearchByMuscle(it.lowercase()) }
    }

    /**
     * Inicializa el RecyclerView para mostrar la lista de ejercicios.
     */
    private fun initRecyclerView() {
        adapter = EjercicioAdapter(exercises)
        binding.rvExercises.layoutManager = LinearLayoutManager(this)
        binding.rvExercises.adapter = adapter
        adapter.setOnItemClickListener(object : onItemClickListener{
            override fun onItemClick(position: Int) {

                var intent = Intent(this@EjerciciosActivity, EjercicioBuscadoActivity::class.java)
                var exercise = exercises[position]
                intent.putExtra("EJERCICIO", exercise)
                startActivity(intent)

            }
        })
    }
    /**
     * Crea una instancia de Retrofit para realizar las llamadas a la API.
     *
     * @return Una instancia de Retrofit configurada con la URL base y un convertidor de JSON.
     */
    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Realiza la búsqueda de ejercicios por músculo utilizando una corutina.
     *
     * @param query El músculo por el cual realizar la búsqueda.
     */
    private fun performSearchByMuscle(query: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                searchByMuscle(query)
            } catch (e: Exception) {
                Log.e("MUSCULO", "Error: ${e.message}")
            }
        }
    }

    /**
     * Realiza la llamada a la API para obtener los ejercicios relacionados con un músculo utilizando una corutina.
     *
     * @param query El músculo por el cual realizar la búsqueda.
     */
    private suspend fun searchByMuscle(query: String){
        CoroutineScope(Dispatchers.IO).launch {

            val call = getRetrofit().create(APIService::class.java).getExercisesByMuscle("exercise/?muscles=$query&language=4")
            val exercisesBody = call.body()
            runOnUiThread {
                if (call.isSuccessful){
                    exercises.clear()
                    if (exercisesBody != null){
                        exercises.addAll(exercisesBody.results)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

}