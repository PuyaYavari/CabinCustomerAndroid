package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.payment

import android.app.Activity

class CabinCustomerFinishTradePaymentRouter(var activity: Activity?) :
    CabinCustomerFinishTradePaymentContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //endregion
}