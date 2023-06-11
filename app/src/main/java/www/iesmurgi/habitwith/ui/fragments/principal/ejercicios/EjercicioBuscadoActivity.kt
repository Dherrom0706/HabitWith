package www.iesmurgi.habitwith.ui.fragments.principal.ejercicios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import www.iesmurgi.habitwith.R
import www.iesmurgi.habitwith.databinding.ActivityEjercicioBuscadoBinding
import www.iesmurgi.habitwith.models.APIService
import www.iesmurgi.habitwith.models.Ejercicio
import www.iesmurgi.habitwith.models.EjercicioImagen

/**
 * Actividad que muestra los detalles de un ejercicio buscado.
 */
class EjercicioBuscadoActivity : AppCompatActivity() {

    private val BASE_URL : String = "https://wger.de/api/v2/"
    private lateinit var binding: ActivityEjercicioBuscadoBinding
    private lateinit var ejercicio: Ejercicio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicioBuscadoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ejercicio = intent.getParcelableExtra("EJERCICIO")!!

        //Log.d("ID_EJERCICIO_BASE",ejercicio.exercise_base.toString())
        ejercicio.exercise_base?.let { performSearchById(it) }
    }
    /**
     * Crea una instancia de Retrofit para realizar las llamadas a la API.
     *
     * @return Una instancia de Retrofit configurada con la URL base y un convertidor de JSON.
     */
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Realiza la búsqueda del ejercicio por su ID utilizando una corutina.
     *
     * @param query El ID del ejercicio a buscar.
     */
    private  fun performSearchById(query: String){
        CoroutineScope(Dispatchers.Main).launch {
            // Llamar a la función suspend dentro de la corutina
            try {
                searchExerciseById(query)
            } catch (e: Exception) {
                Log.e("MUSCULO", "Error: ${e.message}")
            }
        }
    }

    /**
     * Realiza la llamada a la API para obtener los detalles del ejercicio por su ID utilizando una corutina.
     *
     * @param query El ID del ejercicio a buscar.
     */
    private suspend fun searchExerciseById(query: String){

        val apiService = getRetrofit().create(APIService::class.java)
        val exerciseCall = apiService.getExerciseById("exerciseimage/?exercise_base=$query&is_main=true")

        runOnUiThread {
            if (exerciseCall.isSuccessful){
                if (exerciseCall != null){
                    if (exerciseCall.body()?.results != null){
                        Log.d("ID_EJERCICIO_BASE",ejercicio.exercise_base.toString())
                        Picasso.get().load(exerciseCall.body()!!.results[0].image).into(binding.imageView3)
                        var descripcion = ejercicio.description
                            ?.replace("<ol>","")
                            ?.replace("<li>","")
                            ?.replace("</ol>","")
                            ?.replace("</li>","")
                            ?.replace("<p>","")
                            ?.replace("</p>","")
                        binding.descripcion.text = descripcion

                    }
                }else{
                    Toast.makeText(this,getString(R.string.exercise_no_encontrado), Toast.LENGTH_SHORT).show()
                }
            }else{
                Log.d("error","${exerciseCall.message()}  ${exerciseCall.code()}")
                Toast.makeText(this,"Fallo",Toast.LENGTH_SHORT).show()
            }
        }
    }

}