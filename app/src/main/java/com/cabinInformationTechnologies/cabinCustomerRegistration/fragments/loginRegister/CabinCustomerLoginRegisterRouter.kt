package com.cabinInformationTechnologies.cabinCustomerRegistration.fragments.loginRegister

import android.app.Activity

class CabinCustomerLoginRegisterRouter(var activity: Activity?) :
    com.cabinInformationTechnologies.cabinCustomerRegistration.fragments.loginRegister.CabinCustomerLoginRegisterContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router


    //endregion
}