package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.changePassword

import android.app.Activity

class CabinCustomerChangePasswordRouter(var activity: Activity?) :
    com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.changePassword.CabinCustomerChangePasswordContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //endregion
}