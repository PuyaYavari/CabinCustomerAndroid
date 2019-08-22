package ist.cabin.cabinCustomerForgetPassword_fragment

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerForgotpasswordContracts {

    interface View : BaseContracts.View {
        fun disableConfirmationButton()
        fun enableConfirmationButton()
    }

    interface Presenter : BaseContracts.Presenter {
        fun isEmailValid(email: String): Boolean
        fun setInputtedEmail(email: String)
        fun switchConfirmationButton()
        fun confirm()
    }

    interface Interactor : BaseContracts.Interactor {

    }

    interface InteractorOutput : BaseContracts.InteractorOutput {

    }

    interface Router : BaseContracts.Router {
        fun moveToConfirmationPage()
    }

}