package com.cabinInformationTechnologies.cabinCustomerAgreement.fragments.agreement

import android.app.Activity

class CabinCustomerAgreementFragmentRouter(var activity: Activity?) :
    com.cabinInformationTechnologies.cabinCustomerAgreement.fragments.agreement.CabinCustomerAgreementFragmentContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun goForward() {

    }

    //endregion
}