package www.iesmurgi.habitwith.models

/**
 * Clase que representa una receta.
 * @param label Nombre de la receta.
 * @param image URL de la imagen de la receta.
 * @param shareAs URL de la receta compartida.
 */
data class Receta(
    val label : String?,
    val image : String?,
    val shareAs : String?
)

