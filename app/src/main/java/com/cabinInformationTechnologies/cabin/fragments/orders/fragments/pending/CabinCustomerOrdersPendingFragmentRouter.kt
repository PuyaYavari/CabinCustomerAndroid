package com.cabinInformationTechnologies.cabin.fragments.orders.fragments.pending

import android.app.Activity
import androidx.navigation.findNavController
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabin.fragments.orders.CabinCustomerOrdersFragmentDirections
import com.cabinInformationTechnologies.cabin.fragments.orders.PagesIDs

class CabinCustomerOrdersPendingFragmentRouter(var activity: Activity?) :
    CabinCustomerOrdersPendingFragmentContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToDetailsPage() {
        val pageTypeID = PagesIDs.PENDING_PAGE
        val action = CabinCustomerOrdersFragmentDirections.actionOrdersToOrdersDetail(pageTypeID)
        activity!!.findNavController(R.id.nav_host_fragment).navigate(action)
    }

    //endregion
}