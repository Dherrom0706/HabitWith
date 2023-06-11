package www.iesmurgi.habitwith.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import www.iesmurgi.habitwith.R
import www.iesmurgi.habitwith.databinding.MuscleItemBinding
import www.iesmurgi.habitwith.models.Musculo
import www.iesmurgi.habitwith.models.onItemClickListener

/**
 * Adaptador para mostrar una lista de músculos en un RecyclerView.
 * @param muscleList Lista de músculos que se mostrará.
 */
class MuscleAdapter(private val muscleList: MutableList<Musculo>) : RecyclerView.Adapter<MuscleAdapter.MuscleViewHolder>() {

    private lateinit var mListener: onItemClickListener

    /**
     * Método llamado cuando se necesita un nuevo ViewHolder.
     * @param parent El ViewGroup padre.
     * @param viewType El tipo de vista.
     * @return El ViewHolder creado.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MuscleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.muscle_item, parent, false)
        return MuscleViewHolder(itemView, mListener)
    }
    /**
     * Método llamado cuando se vinculan los datos a un ViewHolder.
     * @param holder El ViewHolder al que se vincularán los datos.
     * @param position La posición del elemento en la lista.
     */
    override fun onBindViewHolder(holder: MuscleViewHolder, position: Int) {
        val muscle = muscleList[position]
        holder.bind(muscle)
    }
    /**
     * Devuelve el número total de músculos en la lista.
     * @return El número total de músculos.
     */
    override fun getItemCount(): Int {
        return muscleList.size
    }
    /**
     * Clase ViewHolder para mostrar elementos individuales de músculo.
     * @param itemView La vista para el elemento de músculo.
     * @param listener El listener para eventos de clic en los elementos.
     */
    class MuscleViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        private val binding = MuscleItemBinding.bind(itemView)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

        /**
         *
         * Método para vincular un objeto Musculo a la vista del ViewHolder.
         * @param muscle El objeto Musculo a vincular.
         */
        fun bind(muscle: Musculo) {
            binding.ivMuscleImage.setImageResource(muscle.imageUrl)
            binding.tvMuscleName.text = muscle.muscleName
        }
    }
    /**
     * Establece el listener para los eventos de clic en los elementos.
     * @param listener El listener a establecer.
     */
    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }
}




