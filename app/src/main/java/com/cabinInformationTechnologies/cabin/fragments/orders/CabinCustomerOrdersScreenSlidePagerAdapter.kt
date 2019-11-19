package com.cabinInformationTechnologies.cabin.fragments.orders

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.cabinInformationTechnologies.cabin.fragments.orders.fragments.pending.CabinCustomerOrdersPendingFragment
import com.cabinInformationTechnologies.cabin.fragments.orders.fragments.sent.CabinCustomerOrdersSentFragment
import com.cabinInformationTechnologies.cabin.fragments.orders.fragments.shipping.CabinCustomerOrdersShippingFragment

class CabinCustomerOrdersScreenSlidePagerAdapter(
    fm: FragmentManager, behavior: Int, val manager: CabinCustomerOrdersContracts.FragmentsManager)
    : FragmentStatePagerAdapter(fm, behavior) {

    override fun getCount(): Int = NUM_PAGES

    override fun getItem(position: Int): Fragment {
        return when (position) {
            PagesIDs.PENDING_PAGE -> CabinCustomerOrdersPendingFragment(manager)
            PagesIDs.SHIPPING_PAGE -> CabinCustomerOrdersShippingFragment(manager)
            else -> CabinCustomerOrdersSentFragment(manager)
        }

    }

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        PagesIDs.PENDING_PAGE -> "Bekleyen"
        PagesIDs.SHIPPING_PAGE -> "Kargoda"
        PagesIDs.SENT_PAGE -> "Gönderilen"
        else -> ""
    }

    companion object {
        private const val NUM_PAGES = 3
    }
}