package www.iesmurgi.habitwith.ui.fragments.principal.desglose

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.marginBottom
import com.squareup.picasso.Picasso
import www.iesmurgi.habitwith.R
import www.iesmurgi.habitwith.databinding.ActivityProductoDesglosadoBinding
import www.iesmurgi.habitwith.models.ProductResponse

/**
 * Actividad que muestra el desglose de un producto, incluyendo aditivos y alérgenos.
 */
class ProductoDesglosado : AppCompatActivity() {

    private lateinit var producto: ProductResponse
    private lateinit var binding : ActivityProductoDesglosadoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductoDesglosadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listeners()
    }

    /**
     * Configura los listeners de los elementos interactivos en la vista.
     */
    private fun listeners() {
        producto = intent.getSerializableExtra("PRODUCTO") as ProductResponse
        // Configuracion del LinearLayout del primer ScrollView
        val linearLayoutAditivos = binding.layoutAditivos
        producto.products?.get(0)?.additives_tags?.forEach { tag ->
            val textView = TextView(this)
            val backgroundDrawable = ContextCompat.getDrawable(this, R.drawable.custom_textview_background)
            textView.background = backgroundDrawable
            textView.text = tag.replace("en:","")
            textView.setTextColor(Color.BLUE)
            textView.setPadding(30,30,30,30)
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(0, 0, 0, 30)
            textView.layoutParams = layoutParams
            //setOnClickListener para que cuando se clickee en algun aditivo te describa el mismo
            textView.setOnClickListener {
                val tagText = capitalizeFirstWord(textView.text.toString())
                val webUrl = "https://www.aditivos-alimentarios.com/2016/01/$tagText.html"

                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(webUrl)
                startActivity(intent)
            }
            linearLayoutAditivos.addView(textView)
        }
        // Configuracion del LinearLayout del segundo ScrollView
        val linearLayout2 = binding.layoutAlergenos
        producto.products?.get(0)?.allergens_tags?.forEach { tag ->
            val textView = TextView(this)
            val backgroundDrawable = ContextCompat.getDrawable(this, R.drawable.custom_textview_background)
            textView.background = backgroundDrawable
            textView.text = tag.replace("en:","")
            textView.setTextColor(Color.BLUE)
            textView.setPadding(30,30,30,30)
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(0, 0, 0, 30)
            textView.layoutParams = layoutParams
            linearLayout2.addView(textView)
        }

        binding.tvProducto.text = producto.products?.get(0)?.product_name
        if (producto.products?.get(0)?.image_url != null){
            Picasso.get().load(producto.products?.get(0)?.image_url).into(binding.imProducto)
        }

    }

    /**
     * Capitaliza la primera letra de una cadena de texto.
     *
     * @param str La cadena de texto a capitalizar.
     * @return La cadena de texto con la primera letra capitalizada.
     */
    private fun capitalizeFirstWord(str: String): String {
        if (str.isEmpty()) {
            return str
        }

        val firstChar = str[0].uppercase()
        return firstChar + str.substring(1)
    }

}