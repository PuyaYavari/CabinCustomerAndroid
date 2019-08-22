package ist.cabin.cabinCustomerEmailConfirmation_fragment

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerEmailconfirmationContracts {

    interface View : BaseContracts.View

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