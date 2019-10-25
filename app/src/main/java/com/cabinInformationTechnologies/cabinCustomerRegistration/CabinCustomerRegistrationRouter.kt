package com.cabinInformationTechnologies.cabinCustomerRegistration

import android.app.Activity

class CabinCustomerRegistrationRouter(var activity: Activity?) :
    com.cabinInformationTechnologies.cabinCustomerRegistration.CabinCustomerRegistrationContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}