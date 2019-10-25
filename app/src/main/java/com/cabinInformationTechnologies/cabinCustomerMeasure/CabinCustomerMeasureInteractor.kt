package com.cabinInformationTechnologies.cabinCustomerMeasure

class CabinCustomerMeasureInteractor(var output: com.cabinInformationTechnologies.cabinCustomerMeasure.CabinCustomerMeasureContracts.InteractorOutput?) :
    com.cabinInformationTechnologies.cabinCustomerMeasure.CabinCustomerMeasureContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}