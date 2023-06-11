package www.iesmurgi.habitwith.ui.fragments.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import www.iesmurgi.habitwith.R
import www.iesmurgi.habitwith.adapters.ViewPagerLoginAdapter
import www.iesmurgi.habitwith.databinding.ActivityGestorUsuarioBinding

/**
 *
 * Clase que representa la actividad GestorUsuario, encargada de administrar el proceso de inicio de sesión y registro de usuarios.
 *
 * Esta actividad contiene un ViewPager que muestra dos fragmentos: LoginFragment y RegisterFragment.
 *
 * Los usuarios pueden navegar entre los fragmentos usando pestañas (TabLayout).
 */
class GestorUsuario : AppCompatActivity() {

    private lateinit var binding: ActivityGestorUsuarioBinding
    private val adapter by lazy { ViewPagerLoginAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGestorUsuarioBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setViewPager()
    }

    /**
     *
     * Configura el ViewPager y el TabLayout para mostrar los fragmentos de inicio de sesión y registro.
     */
    private fun setViewPager(){
        binding.pager.adapter = adapter
        val tabLayoutMediator = TabLayoutMediator(binding.tab, binding.pager
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.login)
                }
                1 -> {
                    tab.text = getString(R.string.reg)
                }
            }
        }
        tabLayoutMediator.attach()
    }
}