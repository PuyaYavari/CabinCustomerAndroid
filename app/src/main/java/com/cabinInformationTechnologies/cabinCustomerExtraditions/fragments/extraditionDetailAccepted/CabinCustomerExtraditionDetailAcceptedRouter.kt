package com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionDetailAccepted

import android.app.Activity

class CabinCustomerExtraditionDetailAcceptedRouter(var activity: Activity?) :
    com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionDetailAccepted.CabinCustomerExtraditionDetailAcceptedContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}