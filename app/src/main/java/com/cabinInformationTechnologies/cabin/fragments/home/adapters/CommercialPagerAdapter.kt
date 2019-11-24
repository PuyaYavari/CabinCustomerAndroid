package com.cabinInformationTechnologies.cabin.fragments.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELBanner

class CommercialPagerAdapter(val myDataset: MutableList<MODELBanner>?, val inflater: LayoutInflater): PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val commercialBanner = inflater.inflate(R.layout.cabin_customer_commercial_banner_layout, container, false)
        commercialBanner.findViewById<ImageView>(R.id.commercial_banner_layout).apply {
            //TODO: SET IMAGE AND ON CLICK
        }
        container.addView(commercialBanner)
        return commercialBanner
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int = myDataset?.size ?: 0
}