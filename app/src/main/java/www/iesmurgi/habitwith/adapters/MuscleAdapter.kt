package www.iesmurgi.habitwith.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import www.iesmurgi.habitwith.databinding.MuscleItemBinding
import www.iesmurgi.habitwith.models.Musculo

class MuscleAdapter : RecyclerView.Adapter<MuscleAdapter.MuscleViewHolder>() {
    private val muscleList: MutableList<Musculo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MuscleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MuscleItemBinding.inflate(inflater, parent, false)
        return MuscleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MuscleViewHolder, position: Int) {
        val muscle = muscleList[position]
        holder.bind(muscle)
    }

    override fun getItemCount(): Int {
        return muscleList.size
    }

    fun submitList(list: List<Musculo>) {
        muscleList.clear()
        muscleList.addAll(list)
        notifyDataSetChanged()
    }

    inner class MuscleViewHolder(private val binding: MuscleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(muscle: Musculo) {
            binding.tvMuscleName.text = muscle.muscleName
            // Set other views as needed

            binding.root.setOnClickListener {
                // Handle click on muscle item
                // You can navigate to another fragment/activity here
            }
        }
    }
}