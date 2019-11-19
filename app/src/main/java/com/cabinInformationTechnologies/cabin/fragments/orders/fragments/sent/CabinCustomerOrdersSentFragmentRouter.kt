package com.cabinInformationTechnologies.cabin.fragments.orders.fragments.sent

import android.app.Activity
import androidx.navigation.findNavController
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabin.fragments.orders.CabinCustomerOrdersFragmentDirections
import com.cabinInformationTechnologies.cabin.fragments.orders.PagesIDs
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELOrder

class CabinCustomerOrdersSentFragmentRouter(var activity: Activity?) :
    CabinCustomerOrdersSentFragmentContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToDetailsPage(order: MODELOrder) {
        val pageTypeID = PagesIDs.SENT_PAGE
        val action = CabinCustomerOrdersFragmentDirections.actionOrdersToOrdersDetail(pageTypeID, order)
        activity!!.findNavController(R.id.nav_host_fragment).navigate(action)
    }

    //endregion
}