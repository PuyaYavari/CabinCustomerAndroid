package com.cabinInformationTechnologies.cabinCustomerCamera

import android.app.Activity

class CabinCustomerCameraRouter(var activity: Activity?) :
    com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //endregion
}