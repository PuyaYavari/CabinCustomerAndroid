package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.overview

class CabinCustomerFinishTradeOverviewInteractor(var output: com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.overview.CabinCustomerFinishTradeOverviewContracts.InteractorOutput?) :
    com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.overview.CabinCustomerFinishTradeOverviewContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}