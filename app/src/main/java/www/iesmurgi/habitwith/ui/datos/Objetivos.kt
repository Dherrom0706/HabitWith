package www.iesmurgi.habitwith.ui.datos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import www.iesmurgi.habitwith.databinding.ActivityObjectivosBinding
import www.iesmurgi.habitwith.models.Datos

class Objetivos : AppCompatActivity() {

    private lateinit var binding: ActivityObjectivosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityObjectivosBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        listeners()
    }

    /***
     * Listener asociados a los botones para elegir el objetivo a alcanzar, almacenando el resultado
     * en uno de los campos de la data class Datos.
     */
    private fun listeners() {

        var datos = intent.getParcelableExtra<Datos>("USUARIO")!!
        val intent = Intent(this, DatosPersonales::class.java)

        binding.btnBajar.setOnClickListener {

            datos.objetivo = "bajar"
            intent.putExtra("USUARIO", datos)
            startActivity(intent)

        }
        binding.btnMantener.setOnClickListener {

            datos.objetivo = "mantener"
            intent.putExtra("USUARIO", datos)
            startActivity(intent)

        }
        binding.btnMantener.setOnClickListener {

            datos.objetivo = "fuerza"
            intent.putExtra("USUARIO", datos)
            startActivity(intent)

        }
    }
}