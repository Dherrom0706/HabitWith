package www.iesmurgi.habitwith.ui.fragments.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import www.iesmurgi.habitwith.R
import www.iesmurgi.habitwith.databinding.FragmentLoginBinding
import www.iesmurgi.habitwith.ui.fragments.principal.Principal
import www.iesmurgi.habitwith.ui.fragments.principal.recetas.DietaFragment

/**
 *
 * Fragmento que representa la pantalla de inicio de sesión.
 *
 * Este fragmento permite a los usuarios ingresar con su correo electrónico y contraseña.
 *
 * Utiliza FirebaseAuth para autenticar y manejar las credenciales de inicio de sesión.
 */
class LoginFragment : Fragment() {


    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        listeners()
        return binding.root
    }

    /**
     *
     * Configura los listeners de los elementos de la interfaz de usuario.
     */
    private fun listeners(){
        binding.btnComienzo.setOnClickListener {
            if (!binding.etContra.text.isNullOrEmpty() && !binding.etCorreo.text.isNullOrEmpty())
                signInWithEmailAndPassword(binding.etCorreo.text.toString(), binding.etContra.text.toString())
        }
    }

    /**
     *
     * Inicia sesión con el correo electrónico y la contraseña proporcionados.
     * Si la autenticación es exitosa, se redirige al usuario a la actividad Principal.
     * En caso de error, se muestran mensajes de error apropiados.
     */
    private fun signInWithEmailAndPassword(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    var intent = Intent(context, Principal::class.java)
                    intent.putExtra("ID",firebaseAuth.currentUser?.uid)
                    startActivity(intent)
                    Toast.makeText(context,getString(R.string.bienvenido_de_vuelta), Toast.LENGTH_LONG).show()
                } else {
                    // El inicio de sesión falló
                    when (task.exception) {
                        is FirebaseAuthInvalidUserException -> {
                            // El correo electrónico no está registrado o el usuario fue eliminado
                            Toast.makeText(context,getString(R.string.correo_no_existe), Toast.LENGTH_LONG).show()
                        }
                        is FirebaseAuthInvalidCredentialsException -> {
                            // La contraseña es incorrecta
                            Toast.makeText(context,getString(R.string.contra_incorrecta), Toast.LENGTH_LONG).show()
                        }
                        else -> {
                            Toast.makeText(context,getString(R.string.error_desconocido), Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
    }

}