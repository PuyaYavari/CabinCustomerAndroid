package ist.cabin.cabinCustomerMeasure.fragments.autoTutorial

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerMeasureAutoTutorialContracts {

    interface View : BaseContracts.View {
        fun forwardToShoes()
        fun forwardToOutfit()
        fun backToOutfit()
        fun backToHairstyle()
        fun toPreviousPage()
        fun toNextPage()
    }

    interface Presenter : BaseContracts.Presenter {
        fun showNext()
        fun showPrevious()
    }

    interface Interactor : BaseContracts.Interactor {
        //TODO
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : BaseContracts.Router {
        fun toNextPage()
    }

}