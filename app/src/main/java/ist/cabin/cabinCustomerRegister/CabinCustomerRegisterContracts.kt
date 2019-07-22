package ist.cabin.cabinCustomerRegister

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerRegisterContracts {

    interface View : BaseContracts.View {
        fun setupPage()
        fun enableContinueButton()
        fun disableContinueButton()
        fun selectMan()
        fun unselectMan()
        fun selectWoman()
        fun unselectWoman()
    }

    interface Presenter : BaseContracts.Presenter {
        fun isEmailValid(email: String): Boolean
        fun isPasswordValid(password: String): Boolean
        fun setInputtedEmail(email: String)
        fun setInputtedPassword(password: String)
        fun setInputtedPasswordConfirmation(passwordConfirmation: String)
        fun arePasswordsSame(): Boolean
        fun selectMan()
        fun selectWoman()
        fun switchContinueButton()
        fun continueToNextPage()
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