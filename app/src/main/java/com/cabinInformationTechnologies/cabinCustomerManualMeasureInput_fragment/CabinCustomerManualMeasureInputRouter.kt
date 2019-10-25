package com.cabinInformationTechnologies.cabinCustomerManualMeasureInput_fragment

import android.app.Activity

class CabinCustomerManualMeasureInputRouter(var activity: Activity?) :
    com.cabinInformationTechnologies.cabinCustomerManualMeasureInput_fragment.CabinCustomerManualMeasureInputContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}