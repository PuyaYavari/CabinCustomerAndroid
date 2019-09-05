package ist.cabin.cabinCustomerFinishTrade

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import ist.cabin.cabinCustomerFinishTrade.fragments.address.CabinCustomerFinishTradeAddressFragment
import ist.cabin.cabinCustomerFinishTrade.fragments.overview.CabinCustomerFinishTradeOverviewFragment
import ist.cabin.cabinCustomerFinishTrade.fragments.payment.CabinCustomerFinishTradePaymentFragment

class CabinCustomerFinishTradePagerAdapter(fm: FragmentManager, behavior: Int) :
    FragmentPagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CabinCustomerFinishTradeAddressFragment()
            1 -> CabinCustomerFinishTradePaymentFragment()
            else -> CabinCustomerFinishTradeOverviewFragment()
        }
    }

    override fun getCount(): Int = NUM_PAGES

    companion object {
        private const val NUM_PAGES = 3
    }
}