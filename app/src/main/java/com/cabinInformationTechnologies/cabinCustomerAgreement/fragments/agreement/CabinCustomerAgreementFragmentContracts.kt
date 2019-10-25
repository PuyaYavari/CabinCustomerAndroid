package com.cabinInformationTechnologies.cabinCustomerAgreement.fragments.agreement

object CabinCustomerAgreementFragmentContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        fun goBack()
        fun accept()
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {

    }

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput {

    }

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router {
        fun goForward()
    }

}