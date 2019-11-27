package com.cabinInformationTechnologies.cabinCustomerRegistration

class CabinCustomerRegistrationInteractor(var output: CabinCustomerRegistrationContracts.InteractorOutput?) :
    CabinCustomerRegistrationContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //endregion
}