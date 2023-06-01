package www.iesmurgi.habitwith.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import www.iesmurgi.habitwith.R
import www.iesmurgi.habitwith.adapters.ViewPagerLoginAdapter
import www.iesmurgi.habitwith.adapters.ViewPagerPrincipalAdapter
import www.iesmurgi.habitwith.databinding.ActivityPrincipalBinding

class Principal : AppCompatActivity() {

    private lateinit var binding: ActivityPrincipalBinding
    private val adapter by lazy { ViewPagerPrincipalAdapter(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setViewPager()
    }

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