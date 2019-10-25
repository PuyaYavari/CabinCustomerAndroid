package com.cabinInformationTechnologies.cabinCustomerExtraditions

object CabinCustomerExtraditionsContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        fun showCross()
        fun hideCross()
        fun showBackArrow()
        fun hideBackArrow()
        fun hideHeaderHelperText()
        fun showHeaderHelperText(text: String)
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        //TODO
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {
        //TODO
    }

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router {
        //TODO
    }

}