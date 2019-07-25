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
        fun emailStatusRemoveIcon()
        fun emailStatusEditIcon()
        fun emailStatusCrossIcon()
        fun emailStatusTickIcon()
        fun passwordStatusRemoveIcon()
        fun passwordStatusEditIcon()
        fun passwordStatusCrossIcon()
        fun passwordStatusTickIcon()
        fun passwordConfirmationStatusRemoveIcon()
        fun passwordConfirmationStatusEditIcon()
        fun passwordConfirmationStatusCrossIcon()
        fun passwordConfirmationStatusTickIcon()
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
        fun continueToAgreement()
        fun setEmailIcon()
        fun setPasswordIcon()
        fun setPasswordConfirmationIcon()
        fun setIcons()
        fun removeFocus()
    }

    interface Interactor : BaseContracts.Interactor {

    }

    interface InteractorOutput : BaseContracts.InteractorOutput {

    }

    interface Router : BaseContracts.Router {
        fun moveToAgreementPage()
    }

}