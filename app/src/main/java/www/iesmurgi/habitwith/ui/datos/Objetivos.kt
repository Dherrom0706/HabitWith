package www.iesmurgi.habitwith.ui.datos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import www.iesmurgi.habitwith.databinding.ActivityObjectivosBinding
import www.iesmurgi.habitwith.models.Usuario

/**
 * Actividad que muestra la selección de objetivos para el usuario.
 *
 * Esta actividad permite al usuario elegir su objetivo de salud, como bajar de peso,
 * mantener un peso equilibrado o aumentar la ingesta de proteínas.
 */
class Objetivos : AppCompatActivity() {

    private lateinit var binding: ActivityObjectivosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityObjectivosBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        listeners()
    }

    /**
     *
     * Configura los listeners de los elementos de la interfaz de usuario.
     */
    private fun listeners() {

        var id = intent.getStringExtra("ID")!!
        var usuario = Usuario()
        val intent = Intent(this, DatosPersonales::class.java)

        //Btn a lo relacionado a bajar peso
        binding.btnBajar.setOnClickListener {
            usuario.objetivo = "low-fat"
            intent.putExtra("USUARIO", usuario)
            intent.putExtra("ID",id)
            startActivity(intent)
        }
        //Btn a lo relacionado a mantener
        binding.btnMantener.setOnClickListener {
            usuario.objetivo = "balanced"
            intent.putExtra("USUARIO", usuario)
            intent.putExtra("ID",id)
            startActivity(intent)
        }
        //Btn a lo relacionado a ganar musculo
        binding.btnMantener.setOnClickListener {
            usuario.objetivo = "high-protein"
            intent.putExtra("USUARIO", usuario)
            intent.putExtra("ID",id)
            startActivity(intent)
        }
    }
}