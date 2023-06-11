package www.iesmurgi.habitwith.models

/**
 * Clase que representa la respuesta de una solicitud de recetas.
 * @param hits Lista de RecetaHit que contiene las recetas obtenidas.
 * @param _links Objeto de tipo Links que contiene enlaces relacionados a las recetas.
 */
data class RecetaResponse(
    val hits : List<RecetaHit>,
    val _links : Links
)
