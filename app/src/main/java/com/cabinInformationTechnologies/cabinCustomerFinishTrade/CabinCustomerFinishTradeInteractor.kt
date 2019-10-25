package com.cabinInformationTechnologies.cabinCustomerFinishTrade

class CabinCustomerFinishTradeInteractor(var output: com.cabinInformationTechnologies.cabinCustomerFinishTrade.CabinCustomerFinishTradeContracts.InteractorOutput?) :
    com.cabinInformationTechnologies.cabinCustomerFinishTrade.CabinCustomerFinishTradeContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}