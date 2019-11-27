package com.cabinInformationTechnologies.cabin.fragments.filterDetail

import android.app.Activity

class CabinCustomerFilterDetailRouter(var activity: Activity?) :
    CabinCustomerFilterDetailContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //endregion
}