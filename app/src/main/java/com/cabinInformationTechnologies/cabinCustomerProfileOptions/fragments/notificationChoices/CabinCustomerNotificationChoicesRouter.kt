package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.notificationChoices

import android.app.Activity

class CabinCustomerNotificationChoicesRouter(var activity: Activity?) :
    CabinCustomerNotificationChoicesContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //endregion
}