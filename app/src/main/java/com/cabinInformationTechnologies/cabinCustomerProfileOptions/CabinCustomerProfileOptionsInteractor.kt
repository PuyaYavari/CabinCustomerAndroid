package com.cabinInformationTechnologies.cabinCustomerProfileOptions

class CabinCustomerProfileOptionsInteractor(var output: CabinCustomerProfileOptionsContracts.InteractorOutput?) :
    CabinCustomerProfileOptionsContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //endregion
}