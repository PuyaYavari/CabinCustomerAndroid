package ist.cabin.cabinCustomerAgreement

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerAgreementContracts {

    interface View : BaseContracts.View {
        fun setupPage()
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
        fun goBack()
        fun goForward()
    }

}