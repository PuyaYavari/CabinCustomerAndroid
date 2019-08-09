package ist.cabin.cabinCustomerLogin

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerLoginContracts {

    interface View : BaseContracts.View {
        fun setupPage()
        fun enableLoginButton()
        fun disableLoginbutton()
    }

    interface Presenter : BaseContracts.Presenter {
        fun isEmailValid(email: String): Boolean
        fun setInputtedEmail(emailInput: String)
        fun setInputtedPassword(password: String)
        fun switchLoginButton()
        fun login()
        fun forgetPassword()
    }

    interface Interactor : BaseContracts.Interactor {
        fun login(email: String, password: String)
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
    }

    interface Router : BaseContracts.Router {
        fun moveToHomePage()
        fun moveToForgetpasswordPage()
    }

}