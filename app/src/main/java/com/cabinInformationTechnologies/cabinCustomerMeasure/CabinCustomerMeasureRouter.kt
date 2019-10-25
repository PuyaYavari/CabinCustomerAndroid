package com.cabinInformationTechnologies.cabinCustomerMeasure

import android.app.Activity

class CabinCustomerMeasureRouter(var activity: Activity?) :
    com.cabinInformationTechnologies.cabinCustomerMeasure.CabinCustomerMeasureContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}