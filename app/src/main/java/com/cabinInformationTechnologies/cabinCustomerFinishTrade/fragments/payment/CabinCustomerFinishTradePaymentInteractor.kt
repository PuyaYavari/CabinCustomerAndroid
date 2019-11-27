package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.payment

class CabinCustomerFinishTradePaymentInteractor(var output: CabinCustomerFinishTradePaymentContracts.InteractorOutput?) :
    CabinCustomerFinishTradePaymentContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //endregion
}