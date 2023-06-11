package www.iesmurgi.habitwith.models

import java.io.Serializable

/**
 * Clase que representa la respuesta de un servicio que devuelve una lista de productos.
 * @param products Lista de productos.
 */
data class ProductResponse(
    val products: List<Product>? = null
): Serializable
