package www.iesmurgi.habitwith.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import www.iesmurgi.habitwith.ui.fragments.principal.DesgloseFragment
import www.iesmurgi.habitwith.ui.fragments.principal.DietaFragment
import www.iesmurgi.habitwith.ui.fragments.principal.EjercicioFragment

class ViewPagerPrincipalAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment){

    companion object{
        private const val ARG_OBJECT = "object"
    }

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        if (position == 0){
            return EjercicioFragment()
        }else if (position == 1){
            return DietaFragment()
        }
        return DesgloseFragment()
    }


}