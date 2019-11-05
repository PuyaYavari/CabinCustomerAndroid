package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.cabinInformationTechnologies.cabinCustomerFinishTrade.CabinCustomerFinishTradeContracts
import com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.address.CabinCustomerFinishTradeAddressFragment
import com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.overview.CabinCustomerFinishTradeOverviewFragment
import com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.payment.CabinCustomerFinishTradePaymentFragment

class CabinCustomerFinishTradePagerAdapter(fm: FragmentManager,
                                           behavior: Int,
                                           val callback: CabinCustomerFinishTradeContracts.ChangeAddAddressCallback)
    : FragmentPagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CabinCustomerFinishTradeAddressFragment(callback)
            1 -> CabinCustomerFinishTradePaymentFragment()
            else -> CabinCustomerFinishTradeOverviewFragment()
        }
    }

    override fun getCount(): Int = NUM_PAGES

    companion object {
        private const val NUM_PAGES = 3
    }
}