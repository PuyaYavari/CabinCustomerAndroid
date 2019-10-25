package com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtraditionCurrentItemsList

object CabinCustomerAddExtraditionCurrentItemsListContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {

    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        fun moveForward()
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {
        fun getProductsList()
    }

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router {
        fun moveForward()
    }
}