package com.cabinInformationTechnologies.cabinCustomerRegistration

import android.app.Activity

class CabinCustomerRegistrationRouter(var activity: Activity?) :
    CabinCustomerRegistrationContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //endregion
}