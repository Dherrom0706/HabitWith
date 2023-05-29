package www.iesmurgi.habitwith.ui.fragments.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import www.iesmurgi.habitwith.R
import www.iesmurgi.habitwith.databinding.FragmentRegisterBinding
import www.iesmurgi.habitwith.models.Datos
import www.iesmurgi.habitwith.ui.datos.Objetivos
import www.iesmurgi.habitwith.utils.Utils

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var datosUsuario: Datos

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

    private fun listeners(view: View) {
        view.findViewById<Button>(R.id.btnComienzo).setOnClickListener {
            val email = view.findViewById<TextInputEditText>(R.id.etCorreo).text.toString()
            val password1 = view.findViewById<TextInputEditText>(R.id.etContra).text.toString()
            val password2 = view.findViewById<TextInputEditText>(R.id.etContraConfirm).text.toString()

            if (Utils().checkCorrectPassword(password1, password2, context)) {
                if (Utils().checkCorrectEmail(email, context)) {
                    checkIfEmailExists(email, password1)
                }
            }
        }
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Registro exitoso
                    val user: FirebaseUser? = auth.currentUser
                    Snackbar.make(binding.root, getString(R.string.usuario_registrado), Snackbar.LENGTH_SHORT).show()
                    val intent = Intent(requireContext(), Objetivos::class.java)
                    val datos = Datos()
                    datos.id = auth.currentUser?.uid

                    println(datos)
                    datosUsuario = datos // Almacenar los datos en la variable miembro
                    intent.putExtra("USUARIO", datos)
                    startActivity(intent)
                } else {
                    // Error en el registro
                    val exception = task.exception as? FirebaseAuthException
                    val errorCode = exception?.errorCode
                    val errorMessage = exception?.message
                    //Snackbar.make(binding.root, "Error en el registro: $errorMessage", Snackbar.LENGTH_SHORT).show()
                }
            }
    }

    private fun checkIfEmailExists(email: String, password: String) {
        auth.fetchSignInMethodsForEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val signInMethods = task.result?.signInMethods
                    if (signInMethods.isNullOrEmpty()) {
                        // El correo electrónico no está registrado, puedes proceder con el registro
                        registerUser(email, password)

                    } else {
                        // El correo electrónico ya está registrado
                        Utils().errorToast(requireContext().getString(R.string.error_mail_existe), context)
                    }
                } else {
                    // Error al comprobar el correo electrónico
                    Utils().errorToast(requireContext().getString(R.string.error_mail_desconocido), context)
                }
            }
    }
}