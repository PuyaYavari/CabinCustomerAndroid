package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.overview

import android.app.Activity

class CabinCustomerFinishTradeOverviewRouter(var activity: Activity?) :
    com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.overview.CabinCustomerFinishTradeOverviewContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}