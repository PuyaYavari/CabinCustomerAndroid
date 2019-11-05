package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.main

import android.app.Activity
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress

class CabinCustomerFinishTradeMainRouter(var activity: Activity?) :
    CabinCustomerFinishTradeMainContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToDeliveryAddressDetail(address: MODELAddress?) {
        //TODO: MOVE TO DELIVERY
    }

    override fun moveToInvoiceAddressDetail(address: MODELAddress?) {
        //TODO: MOVE TO INVOICE
    }

    //endregion
}