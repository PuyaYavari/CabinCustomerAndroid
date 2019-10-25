package com.cabinInformationTechnologies.cabinCustomerRegistration

class CabinCustomerRegistrationInteractor(var output: com.cabinInformationTechnologies.cabinCustomerRegistration.CabinCustomerRegistrationContracts.InteractorOutput?) :
    com.cabinInformationTechnologies.cabinCustomerRegistration.CabinCustomerRegistrationContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}