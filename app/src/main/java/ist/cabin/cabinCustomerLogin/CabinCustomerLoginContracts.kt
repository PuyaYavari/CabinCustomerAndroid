package ist.cabin.cabinCustomerLogin

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerLoginContracts {

    interface View : BaseContracts.View {
        fun setupPage()
    }

    interface Presenter : BaseContracts.Presenter {
        fun login()
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