package com.cabinInformationTechnologies.cabin.fragments.discover

import android.app.Activity
import androidx.navigation.findNavController
import com.cabinInformationTechnologies.cabin.R

class CabinCustomerDiscoverRouter(var activity: Activity?) :
    CabinCustomerDiscoverContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToProductDetail(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct) {
        val action = CabinCustomerDiscoverFragmentDirections.actionDiscoverToProductDetail(product, null)
        activity!!.findNavController(R.id.nav_host_fragment).navigate(action)
    }

    override fun moveToFilter() {
        val action = CabinCustomerDiscoverFragmentDirections.actionDiscoverToFilter()
        activity!!.findNavController(R.id.nav_host_fragment).navigate(action)
    }

    //endregion
}