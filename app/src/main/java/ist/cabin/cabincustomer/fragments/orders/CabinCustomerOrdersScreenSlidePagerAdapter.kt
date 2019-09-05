package ist.cabin.cabincustomer.fragments.orders

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import ist.cabin.cabincustomer.fragments.orders.fragments.pending.CabinCustomerOrdersPendingFragment
import ist.cabin.cabincustomer.fragments.orders.fragments.sent.CabinCustomerOrdersSentFragment
import ist.cabin.cabincustomer.fragments.orders.fragments.shipping.CabinCustomerOrdersShippingFragment

class CabinCustomerOrdersScreenSlidePagerAdapter(fm: FragmentManager, behavior: Int) : FragmentStatePagerAdapter(fm, behavior) {

    override fun getCount(): Int = NUM_PAGES

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CabinCustomerOrdersPendingFragment()
            1 -> CabinCustomerOrdersShippingFragment()
            else -> CabinCustomerOrdersSentFragment()
        }

    }

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> "Bekleyen"
        1 -> "Kargoda"
        2 -> "Gönderilen"
        else -> ""
    }

    companion object {
        private const val NUM_PAGES = 3
    }
}