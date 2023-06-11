package www.iesmurgi.habitwith.ui.fragments.principal.ejercicios

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import www.iesmurgi.habitwith.R
import www.iesmurgi.habitwith.adapters.MuscleAdapter
import www.iesmurgi.habitwith.databinding.FragmentMusculoBinding
import www.iesmurgi.habitwith.models.*

/**
 * Fragmento que muestra una lista de músculos.
 */
class MusculosFragment : Fragment() {

    private lateinit var binding: FragmentMusculoBinding
    private lateinit var adapter: MuscleAdapter

    private val muscleList: MutableList<Musculo> = mutableListOf(
        Musculo("9","Triceps",R.drawable.triceps),
        Musculo("5","Trapecio",R.drawable.trapecio),
        Musculo("2","Hombros",R.drawable.hombros),
        Musculo("11","Biceps femoral",R.drawable.biceps_femoral),
        Musculo("13","Branquial",R.drawable.brachialis),
        Musculo("7","Gemelos",R.drawable.gemelos),
        Musculo("8","Gluteos",R.drawable.gluteos),
        Musculo("14","Oblicuo",R.drawable.oblicuo),
        Musculo("4","Pectoral",R.drawable.pectorales),
        Musculo("10","Cuadriceps",R.drawable.quadriceps),
        Musculo("6","Abdominales",R.drawable.abdomen),
        Musculo("1","Biceps",R.drawable.biceps),
        Musculo("12","Dorsal ancho",R.drawable.dorsal_ancho)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMusculoBinding.inflate(inflater, container, false)
        return binding.root
    }
    /**
     * Realiza las acciones después de que la vista ha sido creada, en este caso, crear el recyclerview
     *
     * @param view La vista raíz del fragmento.
     * @param savedInstanceState Los datos previamente guardados del fragmento (opcional).
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    /**
     * Configura el RecyclerView para mostrar la lista de músculos.
     */
    private fun setupRecyclerView() {
        adapter = MuscleAdapter(muscleList)
        binding.rvMuscles.layoutManager = LinearLayoutManager(context)
        binding.rvMuscles.adapter = adapter
        adapter.setOnItemClickListener(object : onItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MusculosFragment.context, EjerciciosActivity::class.java)
                intent.putExtra("MUSCULO",muscleList[position].muscleId)
                startActivity(intent)
            }
        })
    }
}