package com.cabinInformationTechnologies.cabin.fragments.ordersDetail

import android.app.Activity

class CabinCustomerOrdersDetailRouter(var activity: Activity?) : CabinCustomerOrdersDetailContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //endregion
}