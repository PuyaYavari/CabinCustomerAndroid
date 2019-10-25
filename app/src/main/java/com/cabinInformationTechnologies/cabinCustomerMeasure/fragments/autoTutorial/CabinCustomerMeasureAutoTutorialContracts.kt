package com.cabinInformationTechnologies.cabinCustomerMeasure.fragments.autoTutorial

object CabinCustomerMeasureAutoTutorialContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        fun forwardToShoes()
        fun forwardToOutfit()
        fun backToOutfit()
        fun backToHairstyle()
        fun toPreviousPage()
        fun toNextPage()
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        fun showNext()
        fun showPrevious()
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {
        //TODO
    }

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router {
        fun toNextPage()
    }

}