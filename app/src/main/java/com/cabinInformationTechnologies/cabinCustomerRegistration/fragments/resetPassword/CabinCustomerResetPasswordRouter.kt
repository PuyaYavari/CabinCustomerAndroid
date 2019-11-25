package com.cabinInformationTechnologies.cabinCustomerRegistration.fragments.resetPassword

import android.app.Activity

class CabinCustomerResetPasswordRouter(var activity: Activity?) :
    CabinCustomerResetPasswordContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}