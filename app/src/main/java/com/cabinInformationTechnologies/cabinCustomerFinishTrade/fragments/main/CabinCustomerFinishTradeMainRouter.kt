package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.main

import android.app.Activity

class CabinCustomerFinishTradeMainRouter(var activity: Activity?) :
    com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.main.CabinCustomerFinishTradeMainContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}