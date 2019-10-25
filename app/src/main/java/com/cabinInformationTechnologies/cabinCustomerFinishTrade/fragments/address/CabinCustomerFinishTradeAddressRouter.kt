package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.address

import android.app.Activity

class CabinCustomerFinishTradeAddressRouter(var activity: Activity?) :
    com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.address.CabinCustomerFinishTradeAddressContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}