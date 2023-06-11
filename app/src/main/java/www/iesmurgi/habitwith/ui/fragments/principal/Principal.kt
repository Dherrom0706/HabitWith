package www.iesmurgi.habitwith.ui.fragments.principal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.tabs.TabLayoutMediator
import www.iesmurgi.habitwith.R
import www.iesmurgi.habitwith.adapters.ViewPagerLoginAdapter
import www.iesmurgi.habitwith.adapters.ViewPagerPrincipalAdapter
import www.iesmurgi.habitwith.databinding.ActivityPrincipalBinding
import www.iesmurgi.habitwith.ui.fragments.principal.menu.About
import www.iesmurgi.habitwith.ui.fragments.principal.menu.HowTo
import www.iesmurgi.habitwith.ui.fragments.principal.menu.Profile

/**
 *
 * Actividad principal que muestra la interfaz de usuario principal de la aplicación.
 *
 * Esta actividad incluye un ViewPager con pestañas que permiten al usuario navegar entre
 * diferentes secciones, como ejercicio, dieta y desglose. También proporciona un menú de opciones
 * con enlaces a otras actividades, como "Acerca de", "Cómo usar" y "Perfil".
 */
class Principal : AppCompatActivity() {

    private lateinit var binding: ActivityPrincipalBinding
    private val adapter by lazy { ViewPagerPrincipalAdapter(this) }
    private lateinit var id : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        id = intent.getStringExtra("ID").toString()
        Log.d("id_Correcto", id.toString())
        setViewPager()
    }

    /**
     * Crea el menú de opciones en la barra de acciones.
     *
     * @param menu El objeto Menu que se va a inflar.
     * @return Devuelve true para mostrar el menú; de lo contrario, devuelve false.
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_principal, menu)
        return true
    }

    /**
     * Maneja los eventos de selección de elementos del menú de opciones.
     *
     * @param item El elemento de menú seleccionado.
     * @return Devuelve true si el evento se ha manejado correctamente; de lo contrario, devuelve false.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                startActivity(Intent(this, About::class.java))
                return true
            }
            R.id.action_how_to -> {
                startActivity(Intent(this,HowTo::class.java))
                return true
            }
            R.id.action_profile -> {
                var intent = Intent(this, Profile::class.java)
                intent.putExtra("ID", id )
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    /**
     *
     * Configura el ViewPager y las pestañas para navegar entre las diferentes secciones.
     */
    private fun setViewPager(){
        binding.pagerPrincipal.adapter = adapter

        val tabLayoutMediator = TabLayoutMediator(binding.tabPrincipal, binding.pagerPrincipal
        ) { tab, position ->

            when (position) {
                0 -> {
                    tab.text = getString(R.string.ejercicio)
                }
                1 -> {
                    tab.text = getString(R.string.dieta)
                }
                2 -> {
                    tab.text = getString(R.string.desglose)
                }
            }

        }
        tabLayoutMediator.attach()
    }

}