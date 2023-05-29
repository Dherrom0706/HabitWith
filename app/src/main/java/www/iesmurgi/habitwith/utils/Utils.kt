package www.iesmurgi.habitwith.utils

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import www.iesmurgi.habitwith.R

class Utils {

    /***
     *
     */
     fun checkCorrectPassword(password1: String, password2: String,context: Context?) : Boolean{

        var comprobar = false

        val regex = Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$")

        if (password1 == password2){
            if (regex.matches(password1)){
                comprobar = true
            }else{
                if (context != null) {
                    errorToast(context.getString(R.string.error_pass_regex),context)
                }
            }
        }else{
            if (context != null) {
                errorToast(context.getString(R.string.error_pass),context)
            }
        }

        return comprobar
    }

    fun checkCorrectEmail(email : String, context: Context?) : Boolean{

        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return true
            if (context != null) {
                errorToast(context.getString(R.string.error_email),context)
            }
        }else{
            return false
        }

    }

    fun errorToast(message: String, context: Context?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show(   )
    }

}