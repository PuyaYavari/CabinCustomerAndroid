package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.payment

class CabinCustomerFinishTradePaymentInteractor(var output: com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.payment.CabinCustomerFinishTradePaymentContracts.InteractorOutput?) :
    com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.payment.CabinCustomerFinishTradePaymentContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}