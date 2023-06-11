package www.iesmurgi.habitwith.models

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

/**
 * Clase que representa un producto.
 * @param image_url URL de la imagen del producto.
 * @param product_name Nombre del producto.
 * @param additives_tags Etiquetas de aditivos del producto.
 * @param allergens_tags Etiquetas de alérgenos del producto.
 */
data class Product (
    val image_url   : String? = null,
    val product_name: String? = null,
    val additives_tags : List<String> = emptyList(),
    val allergens_tags : List<String> = emptyList()
) : Serializable
