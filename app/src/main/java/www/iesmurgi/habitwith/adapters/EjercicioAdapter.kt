package www.iesmurgi.habitwith.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import www.iesmurgi.habitwith.R
import www.iesmurgi.habitwith.databinding.ActivityEjercicioBuscadoBinding
import www.iesmurgi.habitwith.databinding.ExerciseItemBinding
import www.iesmurgi.habitwith.models.Ejercicio
import www.iesmurgi.habitwith.models.onItemClickListener

/**
 *
 *A daptador para mostrar una lista de ejercicios en un RecyclerView.
 *
 * @param exercices Lista de ejercicios que se mostrará.
 */
class EjercicioAdapter(private val exercices: List<Ejercicio>):RecyclerView.Adapter<EjercicioAdapter.EjercicioViewHolder>() {

    private lateinit var mListener : onItemClickListener

    /**
    *
    * Método llamado cuando se necesita un nuevo ViewHolder.
    * @param parent El ViewGroup padre.
    * @param viewType El tipo de vista.
    * @return El ViewHolder creado.
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EjercicioViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EjercicioViewHolder(layoutInflater.inflate(R.layout.exercise_item,parent,false),mListener)
    }

    /**
     * Devuelve el número total de ejercicios en la lista.
     * @return El número total de ejercicios.
     */
    override fun getItemCount(): Int = exercices.size

    /**
     * Método llamado cuando se vinculan los datos a un ViewHolder.
     * @param holder El ViewHolder al que se vincularán los datos.
     * @param position La posición del elemento en la lista.
     */
    override fun onBindViewHolder(holder: EjercicioViewHolder, position: Int) {
        val item = exercices[position]
        holder.bind(item)
    }

    /**
     * Clase ViewHolder para mostrar elementos individuales de ejercicio.
     * @param view La vista para el elemento de ejercicio.
     * @param listener El listener para eventos de clic en los elementos.
     */
    class EjercicioViewHolder(view: View, listener: onItemClickListener) : RecyclerView.ViewHolder(view) {
        private val binding = ExerciseItemBinding.bind(view)

        init {
            view.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

        /**
         * Método para vincular un objeto Ejercicio a la vista del ViewHolder.
         * @param item El objeto Ejercicio a vincular.
         */
        fun bind(item: Ejercicio) {
            binding.tvExerciseName.text = item.name
        }
    }
    /**
     *
     * Establece el listener para los eventos de clic en los elementos.
     * @param listener El listener a establecer.
     */
    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }
}