package www.iesmurgi.habitwith.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import www.iesmurgi.habitwith.R
import www.iesmurgi.habitwith.models.Musculo

class MuscleAdapter(private val muscleList: MutableList<Musculo>) : RecyclerView.Adapter<MuscleAdapter.MuscleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MuscleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.muscle_item, parent, false)

        return MuscleViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: MuscleViewHolder, position: Int) {
        val muscle = muscleList[position]
        holder.imagenMusculo.setImageResource(muscle.imageUrl)
        holder.nombreMusculo.text = muscle.muscleName
        holder.bind(muscle)
    }

    override fun getItemCount(): Int {
        return muscleList.size
    }

    fun submitList() {
        notifyDataSetChanged()
    }


    class MuscleViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val nombreMusculo = itemView.findViewById<TextView>(R.id.tvMuscleName)
        val imagenMusculo = itemView.findViewById<ImageView>(R.id.ivMuscleImage)
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
        fun bind(muscle: Musculo) {

            }
        }

    private lateinit var mListener : onItemClickListener
    interface onItemClickListener{

        fun onItemClick(position: Int)

    }

    fun setOnItemClickListener(listener: onItemClickListener){

        mListener = listener

    }

}




