package com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionDetailDenied

import android.app.Activity

class CabinCustomerExtraditionDetailDeniedRouter(var activity: Activity?) :
    com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionDetailDenied.CabinCustomerExtraditionDetailDeniedContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}