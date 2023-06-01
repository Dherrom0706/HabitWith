package www.iesmurgi.habitwith.ui.fragments.principal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import www.iesmurgi.habitwith.R
import www.iesmurgi.habitwith.adapters.MuscleAdapter
import www.iesmurgi.habitwith.databinding.FragmentMusculoBinding
import www.iesmurgi.habitwith.models.*


class EjercicioFragment : Fragment() {
    private lateinit var binding: FragmentMusculoBinding
    private lateinit var apiService: ApiService
    private lateinit var muscleAdapter: MuscleAdapter
    private val muscleList: MutableList<Musculo> = mutableListOf(
        Musculo("9","Triceps",R.drawable.ic_baseline_app_registration_24),
        Musculo("5","Trapecio",R.drawable.ic_baseline_app_registration_24),
        Musculo("2","Hombros",R.drawable.ic_baseline_app_registration_24),
        Musculo("11","Biceps femoral",R.drawable.ic_baseline_app_registration_24),
        Musculo("13","Branchialis",R.drawable.ic_baseline_app_registration_24),
        Musculo("7","Calves",R.drawable.ic_baseline_app_registration_24),
        Musculo("8","Gluteos",R.drawable.ic_baseline_app_registration_24),
        Musculo("14","Obliquos",R.drawable.ic_baseline_app_registration_24),
        Musculo("4","Pectoral",R.drawable.ic_baseline_app_registration_24),
        Musculo("10","Quadriceps",R.drawable.ic_baseline_app_registration_24),
        Musculo("6","Abdominales",R.drawable.ic_baseline_app_registration_24),
        Musculo("3","Serratus",R.drawable.ic_baseline_app_registration_24),
        Musculo("15","Soleos",R.drawable.ic_baseline_app_registration_24),
        Musculo("1","Biceps",R.drawable.ic_baseline_app_registration_24),
        Musculo("12","Latissimus dorsi",R.drawable.ic_baseline_app_registration_24)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMusculoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        getMuscles()
    }

    private fun setupRecyclerView() {

        binding.rvMuscles.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = MuscleAdapter(muscleList)
        }

    }

    private fun getMuscles() {

        var adapter = MuscleAdapter(muscleList)
        binding.rvMuscles.adapter = adapter
        adapter.setOnItemClickListener(object : MuscleAdapter.onItemClickListener{

            override fun onItemClick(position: Int) {



            }

        })

    }


}