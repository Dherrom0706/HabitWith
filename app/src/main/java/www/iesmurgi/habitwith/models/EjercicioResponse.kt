package www.iesmurgi.habitwith.models

/**
 * Clase de respuesta que contiene una lista de ejercicios.
 * @param results Lista de objetos Ejercicio que representan los ejercicios.
 */
data class EjercicioResponse(
    var results: List<Ejercicio>
)
