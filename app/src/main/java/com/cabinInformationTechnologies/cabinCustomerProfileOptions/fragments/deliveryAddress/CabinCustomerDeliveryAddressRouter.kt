package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.deliveryAddress

import android.app.Activity

class CabinCustomerDeliveryAddressRouter(var activity: Activity?) :
    CabinCustomerDeliveryAddressContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //endregion
}