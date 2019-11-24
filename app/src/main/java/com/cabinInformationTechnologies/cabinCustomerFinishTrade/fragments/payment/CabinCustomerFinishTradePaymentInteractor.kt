package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.payment

import android.content.Context

class CabinCustomerFinishTradePaymentInteractor(var output: CabinCustomerFinishTradePaymentContracts.InteractorOutput?) :
    CabinCustomerFinishTradePaymentContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun getAgreements(context: Context, id: Int) {

    }

    //endregion
}