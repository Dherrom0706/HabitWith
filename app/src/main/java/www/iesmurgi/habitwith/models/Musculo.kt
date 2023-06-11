package www.iesmurgi.habitwith.models

/**
 *
 * Clase que representa un músculo.
 * @param muscleId Identificador del músculo.
 * @param muscleName Nombre del músculo.
 * @param imageUrl ID de la imagen asociada al músculo.
 */
data class Musculo(
    val muscleId: String,
    val muscleName: String,
    val imageUrl: Int
)