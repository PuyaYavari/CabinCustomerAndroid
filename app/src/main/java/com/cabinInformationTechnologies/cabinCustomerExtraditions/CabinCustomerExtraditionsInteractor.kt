package com.cabinInformationTechnologies.cabinCustomerExtraditions

class CabinCustomerExtraditionsInteractor(var output: com.cabinInformationTechnologies.cabinCustomerExtraditions.CabinCustomerExtraditionsContracts.InteractorOutput?) :
    com.cabinInformationTechnologies.cabinCustomerExtraditions.CabinCustomerExtraditionsContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}