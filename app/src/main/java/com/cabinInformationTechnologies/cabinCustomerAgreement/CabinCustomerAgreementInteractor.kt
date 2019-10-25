package com.cabinInformationTechnologies.cabinCustomerAgreement

class CabinCustomerAgreementInteractor(var output: com.cabinInformationTechnologies.cabinCustomerAgreement.CabinCustomerAgreementContracts.InteractorOutput?) :
    com.cabinInformationTechnologies.cabinCustomerAgreement.CabinCustomerAgreementContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //endregion
}