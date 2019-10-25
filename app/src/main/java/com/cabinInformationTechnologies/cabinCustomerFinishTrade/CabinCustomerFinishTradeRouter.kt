package com.cabinInformationTechnologies.cabinCustomerFinishTrade

import android.app.Activity

class CabinCustomerFinishTradeRouter(var activity: Activity?) :
    com.cabinInformationTechnologies.cabinCustomerFinishTrade.CabinCustomerFinishTradeContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}