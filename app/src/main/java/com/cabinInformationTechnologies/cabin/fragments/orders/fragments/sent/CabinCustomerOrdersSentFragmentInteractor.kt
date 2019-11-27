package com.cabinInformationTechnologies.cabin.fragments.orders.fragments.sent

class CabinCustomerOrdersSentFragmentInteractor(var output: CabinCustomerOrdersSentFragmentContracts.InteractorOutput?) :
    CabinCustomerOrdersSentFragmentContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //endregion
}