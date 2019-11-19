package com.cabinInformationTechnologies.cabin.fragments.orders.fragments.pending

import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELOrder

object CabinCustomerOrdersPendingFragmentContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        //TODO
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        fun moveToDetailsPage(order: MODELOrder)
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {
        //TODO
    }

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router {
        fun moveToDetailsPage(order: MODELOrder)
    }

}