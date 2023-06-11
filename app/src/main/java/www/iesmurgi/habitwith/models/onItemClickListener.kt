package www.iesmurgi.habitwith.models

/**
 * Interfaz para manejar eventos de clic en elementos de la vista.
 */
interface onItemClickListener {
    /**
     * Método invocado cuando se hace clic en un elemento.
     * @param position Posición del elemento en la lista.
     */
    fun onItemClick(position: Int)
}