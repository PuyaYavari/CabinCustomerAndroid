package com.cabinInformationTechnologies.cabin.fragments.orders.fragments.pending

class CabinCustomerOrdersPendingFragmentInteractor(var output: CabinCustomerOrdersPendingFragmentContracts.InteractorOutput?) :
    CabinCustomerOrdersPendingFragmentContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor


    //endregion
}