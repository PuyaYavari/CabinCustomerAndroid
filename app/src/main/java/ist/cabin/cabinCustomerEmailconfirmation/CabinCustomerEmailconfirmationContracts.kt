package ist.cabin.cabinCustomerEmailconfirmation

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerEmailconfirmationContracts {

    interface View : BaseContracts.View {
        fun setupPage()
    }

    interface Presenter : BaseContracts.Presenter {
        fun moveToRootPage()
    }

    interface Interactor : BaseContracts.Interactor {

    }

    interface InteractorOutput : BaseContracts.InteractorOutput {

    }

    interface Router : BaseContracts.Router {
        fun moveToRootPage()
    }

}