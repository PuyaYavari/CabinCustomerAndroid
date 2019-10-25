package com.cabinInformationTechnologies.cabin.fragments.filter

import android.app.Activity
import androidx.navigation.findNavController
import com.cabinInformationTechnologies.cabin.R

class CabinCustomerFilterRouter(var activity: Activity?) : CabinCustomerFilterContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToFilterDetail(filterType: Int) {
        val action = CabinCustomerFilterFragmentDirections
            .actionFilterToFilterDetail(filterType)
        activity!!.findNavController(R.id.nav_host_fragment).navigate(action)
    }

    //endregion
}