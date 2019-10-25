package com.cabinInformationTechnologies.cabinCustomerAgreement.fragments.register

import android.app.Activity

class CabinCustomerRegisterFragmentRouter(var activity: Activity?) :
    com.cabinInformationTechnologies.cabinCustomerAgreement.fragments.register.CabinCustomerRegisterFragmentContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun goForward() {

    }


    //endregion
}