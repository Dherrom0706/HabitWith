package www.iesmurgi.habitwith.models

/**
 * Clase de respuesta que contiene una lista de imágenes de ejercicio.
 * @param results Lista de objetos EjercicioImagen que representan las imágenes de ejercicio.
 */
data class EjercicioImagenResponse(
    val results : List<EjercicioImagen>
)
