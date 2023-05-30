package www.iesmurgi.habitwith.ui.fragments.principal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import www.iesmurgi.habitwith.R
import www.iesmurgi.habitwith.adapters.MuscleAdapter
import www.iesmurgi.habitwith.databinding.FragmentEjercicioBinding
import www.iesmurgi.habitwith.models.*


class EjercicioFragment : Fragment() {
    private lateinit var binding: FragmentEjercicioBinding
    private lateinit var apiService: ApiService
    private lateinit var muscleAdapter: MuscleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEjercicioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        getMuscles()
    }

    private fun setupRecyclerView() {
        muscleAdapter = MuscleAdapter()
        binding.rvMuscles.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = muscleAdapter
        }
    }

    private fun getMuscles() {
        println("Hola")
        apiService = RetrofitClient.createService(ApiService::class.java)
        //List<Musculo>
        val call = apiService.getMuscles()


        call.enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if (response.isSuccessful) {
                    val muscles = response.body()
                    // Aquí puedes realizar las operaciones necesarias con la lista de músculos obtenida
                    // Por ejemplo, actualizar el RecyclerView con los músculos

                    if (muscles != null) {
                        // Actualizar el RecyclerView con los músculos
                        muscleAdapter.submitList(muscles)
                    }
                } else {
                    // Error en la respuesta de la API
                    // Manejar el error según tus necesidades
                }
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                println(t.message)
                println(t.stackTrace)
                println("Fallo mucho de mucho")
            }
        })
    }
}