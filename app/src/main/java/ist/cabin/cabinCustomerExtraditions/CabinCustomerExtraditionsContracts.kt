package ist.cabin.cabinCustomerExtraditions

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerExtraditionsContracts {

    interface View : BaseContracts.View {
        fun showCross()
        fun hideCross()
        fun showBackArrow()
        fun hideBackArrow()
        fun hideHeaderHelperText()
        fun showHeaderHelperText(text: String)
    }

    interface Presenter : BaseContracts.Presenter {
        //TODO
    }

    interface Interactor : BaseContracts.Interactor {
        //TODO
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : BaseContracts.Router {
        //TODO
    }

}