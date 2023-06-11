package www.iesmurgi.habitwith.ui.fragments.principal.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import www.iesmurgi.habitwith.R
import www.iesmurgi.habitwith.databinding.ActivityProfileBinding
import www.iesmurgi.habitwith.ui.fragments.login.GestorUsuario
import www.iesmurgi.habitwith.ui.fragments.principal.Principal

/**
* La clase Profile es una actividad que permite a los usuarios actualizar su perfil.
*/
class Profile : AppCompatActivity() {

    private lateinit var binding : ActivityProfileBinding
    private val database = Firebase.firestore
    private lateinit var user : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNewProfile()
    }

    /**
     * Método privado que configura el nuevo perfil del usuario.
     * Obtiene el ID del usuario de los extras del Intent y establece el comportamiento del botón de actualización.
     * Si los campos de entrada tienen valores no nulos y se ha seleccionado un botón de opción,
     * actualiza los datos en la base de datos de Firebase.
     * Después de actualizar los datos, inicia una nueva actividad y muestra un mensaje de éxito.
     */
    private fun setNewProfile() {
        user = intent.getStringExtra("ID").toString()
        binding.btnActualizar.setOnClickListener {
            if (binding.etAltura.text != null && binding.etEdad.text != null && binding.etPeso.text != null){
                if (binding.radioGroup.checkedRadioButtonId != -1){
                    Log.d("relleno","relleno")
                    val documentReference = database.collection("usuarios").document(user)
                    documentReference.update("altura",binding.etAltura.text.toString())
                    documentReference.update("edad",binding.etEdad.text.toString())
                    documentReference.update("peso",binding.etPeso.text.toString())
                    if (binding.rbBajar.isSelected){
                        documentReference.update("objetivo","low-fat")
                    }
                    if (binding.rbEstable.isSelected){
                        documentReference.update("objetivo","balanced")
                    }
                    if (binding.rbMusculo.isSelected){
                        documentReference.update("objetivo","high-protein")
                    }

                    startActivity(Intent(this,GestorUsuario::class.java))
                    Toast.makeText(this,getString(R.string.actualizado), Toast.LENGTH_LONG).show()

                }
            }else{
                Toast.makeText(this,getString(R.string.vacio), Toast.LENGTH_LONG).show()
            }
        }
    }
}