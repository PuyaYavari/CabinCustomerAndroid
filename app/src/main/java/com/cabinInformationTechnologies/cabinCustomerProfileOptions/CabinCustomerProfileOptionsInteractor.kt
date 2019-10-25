package com.cabinInformationTechnologies.cabinCustomerProfileOptions

class CabinCustomerProfileOptionsInteractor(var output: com.cabinInformationTechnologies.cabinCustomerProfileOptions.CabinCustomerProfileOptionsContracts.InteractorOutput?) :
    com.cabinInformationTechnologies.cabinCustomerProfileOptions.CabinCustomerProfileOptionsContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //endregion
}