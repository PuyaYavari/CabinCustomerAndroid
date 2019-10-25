package com.cabinInformationTechnologies.cabin.fragments.filterDetail

class CabinCustomerFilterDetailInteractor(var output: CabinCustomerFilterDetailContracts.InteractorOutput?) :
    CabinCustomerFilterDetailContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}