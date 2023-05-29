package www.iesmurgi.habitwith.ui.datos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import www.iesmurgi.habitwith.databinding.ActivityDatosPersonalesBinding
import www.iesmurgi.habitwith.models.Datos
import www.iesmurgi.habitwith.ui.Principal


class DatosPersonales : AppCompatActivity() {

    private lateinit var binding: ActivityDatosPersonalesBinding
    private var mujer : Boolean = false
    private var hombre : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatosPersonalesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        listeners()
    }

    private fun listeners(){

        var datos = intent.getParcelableExtra<Datos>("USUARIO")!!
        val intent = Intent(this, Principal::class.java)
        var altura: String = ""
        var peso :String = ""
        var edad : String = ""
        binding.btnSiguiente.setOnClickListener {

            if (!binding.etAltura.text?.isEmpty()!!){
                altura = binding.etAltura.text.toString()
            }

            if (!binding.etPeso.text?.isEmpty()!!){
                peso = binding.etPeso.text.toString()
            }

            if (!binding.etEdad.text?.isEmpty()!!){
                edad = binding.etAltura.text.toString()
            }

            if (altura.isNotEmpty() and peso.isNotEmpty() and edad.isNotEmpty() and (hombre or mujer)){
                datos.altura = altura
                datos.peso = peso
                datos.edad = edad
                if (hombre)
                    datos.sexo = "masculino"
                if (mujer)
                    datos.sexo = "femenino"

                intent.putExtra("USUARIO",datos)
                startActivity(intent)
            }

        }

        binding.btnHombre.setOnClickListener {
            hombre = true
        }

        binding.btnMujer.setOnClickListener {
            mujer = true
        }



    }

}