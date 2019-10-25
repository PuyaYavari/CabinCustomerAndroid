package com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.congratulations

class CabinCustomerExtraditionsCongratulationsInteractor(var output: com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.congratulations.CabinCustomerExtraditionsCongratulationsContracts.InteractorOutput?) :
    com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.congratulations.CabinCustomerExtraditionsCongratulationsContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}