package com.cabinInformationTechnologies.cabin.fragments.orders

class CabinCustomerOrdersInteractor(var output: CabinCustomerOrdersContracts.InteractorOutput?) :
    CabinCustomerOrdersContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //endregion
}