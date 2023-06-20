package www.iesmurgi.habitwith.ui.fragments.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import www.iesmurgi.habitwith.R
import www.iesmurgi.habitwith.databinding.FragmentRegisterBinding
import www.iesmurgi.habitwith.ui.datos.Objetivos

/**
 *
 * Fragmento que representa la pantalla de registro de usuario.
 *
 * Este fragmento permite a los usuarios crear una cuenta utilizando su correo electrónico y contraseña.
 *
 * También proporciona la opción de registro con Google.
 */
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        // Inicializar binding y auth
        binding = FragmentRegisterBinding.bind(view)
        auth = FirebaseAuth.getInstance()

        listeners(view)
        return view
    }

    /**
     *
     * Configura los listeners de los elementos de la interfaz de usuario.
     */
    private fun listeners(view: View) {
        binding.btnComienzo.setOnClickListener {
            val email = view.findViewById<TextInputEditText>(R.id.etCorreo).text.toString()
            val password1 = view.findViewById<TextInputEditText>(R.id.etContra).text.toString()
            val password2 = view.findViewById<TextInputEditText>(R.id.etContraConfirm).text.toString()

            if (checkCorrectPassword(password1, password2)) {
                if (checkCorrectEmail(email)) {
                    checkIfEmailExists(email, password1)
                }else{
                    Toast.makeText(activity,(requireContext().getString(R.string.correo_no_valido)), Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(activity,(requireContext().getString(R.string.contra_no_coincide)), Toast.LENGTH_LONG).show()
            }
        }
    }
    /**
     *
     * Registra al usuario con el correo electrónico y contraseña proporcionados.
     */
    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Registro exitoso
                    val intent = Intent(requireContext(), Objetivos::class.java)

                    intent.putExtra("ID",auth.currentUser?.uid)
                    startActivity(intent)
                } else {

                    Toast.makeText(activity,(requireContext().getString(R.string.error_registro)), Toast.LENGTH_LONG).show()
                }
            }
    }

    /**
     *
     * Verifica si el correo electrónico ya está registrado.
     * Si no está registrado, procede a registrar al usuario.
     */
    private fun checkIfEmailExists(email: String, password: String) {
        auth.fetchSignInMethodsForEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val signInMethods = task.result?.signInMethods
                    if (signInMethods.isNullOrEmpty()) {
                        // El correo electrónico no está registrado
                        registerUser(email, password)
                    } else {
                        // El correo electrónico ya está registrado
                        Toast.makeText(activity,(requireContext().getString(R.string.error_mail_existe)), Toast.LENGTH_LONG).show()
                    }
                } else {
                    // Error al comprobar el correo electrónico
                    Toast.makeText(activity,(requireContext().getString(R.string.error_mail_desconocido)), Toast.LENGTH_LONG).show()
                }
            }
    }

    /**
     *
     * Verifica si las contraseñas ingresadas coinciden.
     */
    private fun checkCorrectPassword(contra1: String, contra2: String) : Boolean{
        return contra1 == contra2
    }

    /**
     *
     * Verifica si el formato del correo electrónico es válido.
     */
    fun checkCorrectEmail(email : String) : Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}