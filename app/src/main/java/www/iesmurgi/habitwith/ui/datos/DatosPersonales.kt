package www.iesmurgi.habitwith.ui.datos

import android.content.Intent
import com.google.firebase.firestore.FirebaseFirestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import www.iesmurgi.habitwith.R
import www.iesmurgi.habitwith.databinding.ActivityDatosPersonalesBinding
import www.iesmurgi.habitwith.models.Usuario
import www.iesmurgi.habitwith.ui.fragments.principal.Principal

/**
 *
 * Clase que representa la actividad de Datos Personales, donde se recopilan y actualizan los datos personales del usuario.
 * Esta actividad permite al usuario ingresar su altura, peso, edad y seleccionar su sexo.
 * Los datos ingresados se guardan en un objeto de tipo Usuario y se actualizan en la base de datos.
 * Después de ingresar los datos, se redirige al usuario a la actividad Principal.
 */
class DatosPersonales : AppCompatActivity() {

    private lateinit var binding: ActivityDatosPersonalesBinding
    private val db = FirebaseFirestore.getInstance()
    private var mujer : Boolean = false
    private var hombre : Boolean = false
    private var altura: String = ""
    private var peso :String = ""
    private var edad : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatosPersonalesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        listeners()
    }

    /**
     *
     * Configura los listeners para los botones y elementos de la interfaz de usuario.
     */
    private fun listeners(){
        var usuario = intent.getParcelableExtra<Usuario>("USUARIO")!!
        val id = intent.getStringExtra("ID")
        val intent = Intent(this, Principal::class.java)
        binding.btnHombre.setOnClickListener {
            hombre = true
        }
        binding.btnMujer.setOnClickListener {
            mujer = true
        }
        binding.btnSiguiente.setOnClickListener {
            if (!binding.etAltura.text?.isEmpty()!!)
                altura = binding.etAltura.text.toString()
            if (!binding.etPeso.text?.isEmpty()!!)
                peso = binding.etPeso.text.toString()
            if (!binding.etEdad.text?.isEmpty()!!)
                edad = binding.etEdad.text.toString()

            if (altura.isNotEmpty() and peso.isNotEmpty() and edad.isNotEmpty() and (hombre or mujer)){
                usuario.altura = altura
                usuario.peso = peso
                usuario.edad = edad
                if (hombre)
                    usuario.sexo = "masculino"
                if (mujer)
                    usuario.sexo = "femenino"

                intent.putExtra("ID",id)
                intent.putExtra("USUARIO",usuario)
                if (id != null && usuario != null) {
                    actualizarUsuario(id,usuario)
                }
                startActivity(intent)
            }
        }
    }
    /**
     *
     * Actualiza los datos del usuario en la base de datos.
     * @param userId ID del usuario.
     * @param usuario Objeto de tipo Usuario con los datos actualizados.
     */
    private fun actualizarUsuario(userId: String, usuario: Usuario) {
        val usuariosRef = db.collection("usuarios")
        val usuarioDoc = usuariosRef.document(userId)
        usuarioDoc.set(usuario)
            .addOnSuccessListener {
                Toast.makeText(this,getString(R.string.bienvenido), Toast.LENGTH_LONG).show()
                Log.d("success","correcto")
            }
            .addOnFailureListener {
                Toast.makeText(this,getString(R.string.register_failed), Toast.LENGTH_LONG).show()
                Log.d("Error"," ${it.message}")
            }
    }
}