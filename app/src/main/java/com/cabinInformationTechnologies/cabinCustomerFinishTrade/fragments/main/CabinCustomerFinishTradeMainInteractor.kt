package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.main

class CabinCustomerFinishTradeMainInteractor(var output: com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.main.CabinCustomerFinishTradeMainContracts.InteractorOutput?) :
    com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.main.CabinCustomerFinishTradeMainContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}