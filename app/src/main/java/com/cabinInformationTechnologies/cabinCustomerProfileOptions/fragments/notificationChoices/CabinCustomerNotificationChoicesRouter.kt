package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.notificationChoices

import android.app.Activity

class CabinCustomerNotificationChoicesRouter(var activity: Activity?) :
    com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.notificationChoices.CabinCustomerNotificationChoicesContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //endregion
}