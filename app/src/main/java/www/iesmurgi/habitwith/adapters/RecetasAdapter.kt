package www.iesmurgi.habitwith.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import www.iesmurgi.habitwith.R
import www.iesmurgi.habitwith.databinding.RecipeItemBinding
import www.iesmurgi.habitwith.models.Receta
import www.iesmurgi.habitwith.models.RecetaHit
import www.iesmurgi.habitwith.models.onItemClickListener

/**
 * Adaptador para mostrar una lista de recetas en un RecyclerView.
 *
 * @param recetas Lista de recetas que se mostrará.
 */
class RecetasAdapter(private val recetas: List<RecetaHit>) : RecyclerView.Adapter<RecetasAdapter.RecetasViewHolder>() {

    private lateinit var mListener: onItemClickListener

    /**
     *
     * Método llamado cuando se necesita un nuevo ViewHolder.
     * @param parent El ViewGroup padre.
     * @param viewType El tipo de vista.
     * @return El ViewHolder creado.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecetasViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RecetasViewHolder(layoutInflater.inflate(R.layout.recipe_item, parent, false), mListener)
    }
    /**
     *
     * Método llamado cuando se vinculan los datos a un ViewHolder.
     * @param holder El ViewHolder al que se vincularán los datos.
     * @param position La posición del elemento en la lista.
     */
    override fun onBindViewHolder(holder: RecetasViewHolder, position: Int) {
        val recipe = recetas[position]
        holder.bind(recipe)
    }
    /**
     *
     * Devuelve el número total de recetas en la lista.
     * @return El número total de recetas.
     */
    override fun getItemCount(): Int = recetas.size
    /**
     *
     * Clase ViewHolder para mostrar elementos individuales de receta.
     *
     * @param itemView La vista para el elemento de receta.
     *
     * @param listener El listener para eventos de clic en los elementos.
     */
    class RecetasViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        private val binding = RecipeItemBinding.bind(itemView)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

        /**
         *
         * Método para vincular un objeto RecetaHit a la vista del ViewHolder.
         *
         * @param recipe El objeto RecetaHit a vincular.
         */
        fun bind(recipe: RecetaHit) {
            if (!recipe.recipe.image.isNullOrBlank()) {
                Picasso.get().load(recipe.recipe.image).into(binding.ivReceta)
            }

            Log.d("recetainfo", "${recipe.recipe.label}")
            binding.titulo.text = recipe.recipe.label.toString()
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