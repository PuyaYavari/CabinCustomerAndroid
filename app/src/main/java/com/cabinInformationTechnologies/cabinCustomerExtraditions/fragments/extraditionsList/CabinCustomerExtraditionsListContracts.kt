package com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList

object CabinCustomerExtraditionsListContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        fun addExtraditionListener()
        fun setupNoExtraditionList()
        fun setupExtraditionsList()
        fun moveToExtraditionDetail()
        fun moveToExtraditionDetailDenied()
        fun moveToExtraditionDetailAccepted()
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        fun addExtradition()
        fun setupPage()
        fun moveToExtraditionDetail()
        fun moveToExtraditionDetailDenied()
        fun moveToExtraditionDetailAccepted()
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {
        fun getInitialData(): Unit?
    }

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router {
        fun moveToAddExtraditionPage()
        fun moveToExtraditionDetail()
        fun moveToExtraditionDetailDenied()
        fun moveToExtraditionDetailAccepted()
    }

    interface ExtraditionBox {
        fun getType(): Int
        fun getStatusID(): Int
    }

}