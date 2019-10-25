package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.address

class CabinCustomerFinishTradeAddressInteractor(var output: com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.address.CabinCustomerFinishTradeAddressContracts.InteractorOutput?) :
    com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.address.CabinCustomerFinishTradeAddressContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}