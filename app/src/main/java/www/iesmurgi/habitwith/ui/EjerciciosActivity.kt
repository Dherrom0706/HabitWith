package www.iesmurgi.habitwith.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import www.iesmurgi.habitwith.R

class EjerciciosActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicios)
        findViewById<TextView>(R.id.texto).text = intent.getStringExtra("POSITION").toString()
    }
}