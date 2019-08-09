package ist.cabin.cabinCustomerOrders

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import ist.cabin.cabinCustomerOrders.fragments.pending.CabinCustomerOrdersPendingFragment
import ist.cabin.cabinCustomerOrders.fragments.sent.CabinCustomerOrdersSentFragment
import ist.cabin.cabinCustomerOrders.fragments.shipping.CabinCustomerOrdersShippingFragment

class CabinCustomerOrdersScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private val NUM_PAGES = 3

    override fun getCount(): Int = NUM_PAGES

    override fun getItem(position: Int): Fragment {
        if (position == 0)
            return CabinCustomerOrdersPendingFragment()
        else if (position == 1)
            return CabinCustomerOrdersShippingFragment()
        else
            return CabinCustomerOrdersSentFragment()

    }

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> "Bekleyen"
        1 -> "Kargoda"
        2 -> "Gönderilen"
        else -> ""
    }
}