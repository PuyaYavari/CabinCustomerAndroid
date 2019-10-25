package com.cabinInformationTechnologies.cabinCustomerAgreement.fragments.register

class CabinCustomerRegisterFragmentInteractor(var output: com.cabinInformationTechnologies.cabinCustomerAgreement.fragments.register.CabinCustomerRegisterFragmentContracts.InteractorOutput?) :
    com.cabinInformationTechnologies.cabinCustomerAgreement.fragments.register.CabinCustomerRegisterFragmentContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor



    //endregion
}