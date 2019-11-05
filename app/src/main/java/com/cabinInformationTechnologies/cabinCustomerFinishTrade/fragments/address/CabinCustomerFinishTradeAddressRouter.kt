package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.address

import android.app.Activity

class CabinCustomerFinishTradeAddressRouter(var activity: Activity?) :
    CabinCustomerFinishTradeAddressContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //endregion
}