package com.cabinInformationTechnologies.cabinCustomerAgreement.fragments.agreement

class CabinCustomerAgreementFragmentInteractor(var output: com.cabinInformationTechnologies.cabinCustomerAgreement.fragments.agreement.CabinCustomerAgreementFragmentContracts.InteractorOutput?) :
    com.cabinInformationTechnologies.cabinCustomerAgreement.fragments.agreement.CabinCustomerAgreementFragmentContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //endregion
}