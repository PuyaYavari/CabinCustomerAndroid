package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.personalDataOptions

import android.app.Activity

class CabinCustomerPersonalDataOptionsRouter(var activity: Activity?) :
    CabinCustomerPersonalDataOptionsContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //endregion
}