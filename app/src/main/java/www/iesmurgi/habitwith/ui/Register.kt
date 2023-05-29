package www.iesmurgi.habitwith.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import www.iesmurgi.habitwith.adapters.ViewPagerLoginAdapter
import www.iesmurgi.habitwith.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val adapter by lazy { ViewPagerLoginAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setViewPager()
    }

    private fun setViewPager(){
        binding.pager.adapter = adapter

        val tabLayoutMediator = TabLayoutMediator(binding.tab, binding.pager
        ) { tab, position ->

            when (position) {
                0 -> {
                    tab.text = "LOGIN"
                    //tab.setIcon(R.drawable.ic_baseline_login_24)
                }
                1 -> {
                    tab.text = "REGISTRARSE"
                    //tab.setIcon(R.drawable.ic_baseline_app_registration_24)
                }
            }

        }
        tabLayoutMediator.attach()
    }

}