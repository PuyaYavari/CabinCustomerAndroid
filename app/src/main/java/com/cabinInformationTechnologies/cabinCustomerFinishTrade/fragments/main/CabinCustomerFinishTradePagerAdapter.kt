package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class CabinCustomerFinishTradePagerAdapter(fm: FragmentManager, behavior: Int) :
    FragmentPagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.address.CabinCustomerFinishTradeAddressFragment()
            1 -> com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.payment.CabinCustomerFinishTradePaymentFragment()
            else -> com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.overview.CabinCustomerFinishTradeOverviewFragment()
        }
    }

    override fun getCount(): Int =
        com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.main.CabinCustomerFinishTradePagerAdapter.Companion.NUM_PAGES

    companion object {
        private const val NUM_PAGES = 3
    }
}