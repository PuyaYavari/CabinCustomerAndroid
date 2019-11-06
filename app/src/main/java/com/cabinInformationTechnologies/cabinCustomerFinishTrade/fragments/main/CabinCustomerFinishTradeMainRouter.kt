package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.main

import android.app.Activity
import androidx.navigation.findNavController
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress

class CabinCustomerFinishTradeMainRouter(var activity: Activity?) :
    CabinCustomerFinishTradeMainContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToDeliveryAddressDetail(address: MODELAddress?) {
        val action =
            CabinCustomerFinishTradeMainFragmentDirections.actionFinishTradeMainToDeliveryAddress(
                address
            )
        activity!!.findNavController(R.id.finish_trade_navhost).navigate(action)
    }

    override fun moveToInvoiceAddressDetail(address: MODELAddress?) {
        val action =
            CabinCustomerFinishTradeMainFragmentDirections.actionFinishTradeMainToInvoiceAddress(
                address
            )
        activity!!.findNavController(R.id.finish_trade_navhost).navigate(action)
    }

    //endregion
}