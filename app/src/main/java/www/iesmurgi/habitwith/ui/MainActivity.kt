package www.iesmurgi.habitwith.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import www.iesmurgi.habitwith.databinding.ActivityMainBinding
import www.iesmurgi.habitwith.ui.fragments.login.GestorUsuario

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        listeners()
    }

    private fun listeners(){
        binding.btnComienzo.setOnClickListener {
            startActivity(Intent(this, GestorUsuario::class.java))
        }
    }
}