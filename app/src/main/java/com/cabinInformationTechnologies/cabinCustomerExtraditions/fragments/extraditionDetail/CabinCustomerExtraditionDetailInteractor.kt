package com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionDetail

class CabinCustomerExtraditionDetailInteractor(var output: com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionDetail.CabinCustomerExtraditionDetailContracts.InteractorOutput?) :
    com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionDetail.CabinCustomerExtraditionDetailContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}