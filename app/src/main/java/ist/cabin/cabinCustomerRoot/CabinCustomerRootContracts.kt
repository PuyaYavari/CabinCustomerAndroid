package ist.cabin.cabinCustomerRoot

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerRootContracts {

    interface View : BaseContracts.View {
        fun setupPage()
    }

    interface Presenter : BaseContracts.Presenter {
        fun moveToLoginPage()
        fun moveToRegisterPage()
        fun moveToInfoPage()
    }

    interface Interactor : BaseContracts.Interactor

    interface InteractorOutput : BaseContracts.InteractorOutput

    interface Router : BaseContracts.Router {
        fun moveToLoginPage()
        fun moveToRegisterPage()
        fun moveToInfoPage()
    }

}