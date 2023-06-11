package www.iesmurgi.habitwith.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import www.iesmurgi.habitwith.ui.fragments.principal.desglose.DesgloseFragment
import www.iesmurgi.habitwith.ui.fragments.principal.recetas.DietaFragment
import www.iesmurgi.habitwith.ui.fragments.principal.ejercicios.MusculosFragment

/**
 *Adaptador para ViewPager que muestra diferentes fragmentos en función de la posición.
 *
 * @param fragment Actividad de fragmento utilizada para el adaptador.
 */
class ViewPagerPrincipalAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    /**
     *
     * Devuelve el número total de fragmentos en el ViewPager.
     * @return El número total de fragmentos.
     */
    override fun getItemCount(): Int = 3

    /**
     *
     * Crea y devuelve un nuevo fragmento en función de la posición.
     * @param position La posición del fragmento.
     * @return El fragmento correspondiente a la posición.
     */
    override fun createFragment(position: Int): Fragment {
        if (position == 0) {
            return MusculosFragment()
        } else if (position == 1) {
            return DietaFragment()
        }
        return DesgloseFragment()
    }
}