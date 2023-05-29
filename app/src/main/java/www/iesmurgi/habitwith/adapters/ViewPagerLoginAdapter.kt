package www.iesmurgi.habitwith.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import www.iesmurgi.habitwith.ui.fragments.login.LoginFragment
import www.iesmurgi.habitwith.ui.fragments.login.RegisterFragment

class ViewPagerLoginAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {

    companion object{
        private const val ARG_OBJECT = "object"
    }

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        if (position== 0){
            return LoginFragment()
        }
        return RegisterFragment()
    }



}