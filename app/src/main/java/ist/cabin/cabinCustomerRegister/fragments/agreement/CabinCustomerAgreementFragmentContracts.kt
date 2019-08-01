package ist.cabin.cabinCustomerRegister.fragments.agreement

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerAgreementFragmentContracts {

    interface View : BaseContracts.View {
        fun setupPage(view: android.view.View)
    }

    interface Presenter : BaseContracts.Presenter {
        fun goBack()
        fun accept()
    }

    interface Interactor : BaseContracts.Interactor {

    }

    interface InteractorOutput : BaseContracts.InteractorOutput {

    }

    interface Router : BaseContracts.Router {
        fun goForward()
    }

}