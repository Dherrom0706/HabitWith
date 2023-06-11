package www.iesmurgi.habitwith.ui.fragments.principal.desglose

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import www.iesmurgi.habitwith.R
import www.iesmurgi.habitwith.databinding.FragmentDesgloseBinding
import www.iesmurgi.habitwith.models.APIService

/**
 * Fragmento que muestra la funcionalidad de desglose de productos mediante el escaneo de códigos de barras.
 */
class DesgloseFragment : Fragment() {

    private lateinit var binding: FragmentDesgloseBinding
    private val BASE_URL : String = "https://world.openfoodfacts.org/api/v2/"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDesgloseBinding.inflate(inflater, container, false)

        listeners()
        return binding.root
    }

    /**
     * Configura los listeners de los botones y elementos interactivos.
     */
    private fun listeners() {
        binding.btnEscaner.setOnClickListener {
            IntentIntegrator.forSupportFragment(this)
                .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
                .setTorchEnabled(false)
                .setBeepEnabled(true)
                .setOrientationLocked(true)
                .setCaptureActivity(CapActivity::class.java)
                .setPrompt(getString(R.string.scan))
                .initiateScan()
        }
    }

    /**
     * Se llama cuando se obtiene un resultado de la actividad de escaneo de código de barras.
     *
     * @param requestCode El código de solicitud de la actividad de escaneo.
     * @param resultCode El código de resultado de la actividad de escaneo.
     * @param data Los datos de retorno de la actividad de escaneo.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)

        if (result != null){
            if (result.contents != null){
                performSearchProductByCode(result.contents )
            }else{
                Toast.makeText(context, "Cancelado",Toast.LENGTH_LONG).show()
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }

    }
    /**
     * Crea una instancia de Retrofit con la configuración necesaria.
     *
     * @return La instancia de Retrofit.
     */
    private fun getRetrofit(): Retrofit {
        val gson = GsonBuilder().setLenient().create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    /**
     * Realiza la búsqueda de un producto utilizando el código de barras.
     *
     * @param query El código de barras del producto.
     */
    private fun performSearchProductByCode(query: String) {
        // Lanzar una corutina
        CoroutineScope(Dispatchers.Main).launch {
            // Llamar a la función suspend dentro de la corutina
            try {
                searchProductByCode(query)
            } catch (e: Exception) {
                Log.e("Producto", "Error: ${e.message}")
            }
        }
    }

    /**
     * Realiza la búsqueda de un producto utilizando el código de barras y muestra los resultados en una actividad.
     *
     * @param query El código de barras del producto.
     */
    private suspend fun searchProductByCode(query: String){
        CoroutineScope(Dispatchers.IO).launch {

            val call = getRetrofit().create(APIService::class.java).getProductByCode("search?code=$query&lang=es")

            activity?.runOnUiThread {
                if (call.isSuccessful){
                    val intent = Intent(context, ProductoDesglosado::class.java)
                    intent.putExtra("PRODUCTO",call.body())
                    startActivity(intent)
                }
            }
        }
    }

}