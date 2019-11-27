package com.cabinInformationTechnologies.cabin.fragments.orders.fragments.shipping

class CabinCustomerOrdersShippingFragmentInteractor(var output: CabinCustomerOrdersShippingFragmentContracts.InteractorOutput?) :
    CabinCustomerOrdersShippingFragmentContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //endregion
}