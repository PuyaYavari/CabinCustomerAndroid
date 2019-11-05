package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.main

class CabinCustomerFinishTradeMainInteractor(var output: CabinCustomerFinishTradeMainContracts.InteractorOutput?) :
    CabinCustomerFinishTradeMainContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}