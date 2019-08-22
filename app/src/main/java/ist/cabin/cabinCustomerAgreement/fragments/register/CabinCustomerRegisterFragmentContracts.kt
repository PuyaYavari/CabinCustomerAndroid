package ist.cabin.cabinCustomerAgreement.fragments.register

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerRegisterFragmentContracts {

    interface View : BaseContracts.View {
        fun setupPage(view: android.view.View)
        fun enableContinueButton()
        fun disableContinueButton()
        fun selectMan()
        fun unselectMan()
        fun selectWoman()
        fun unselectWoman()
        fun selectMan(view: android.view.View)
        fun unselectMan(view: android.view.View)
        fun selectWoman(view: android.view.View)
        fun unselectWoman(view: android.view.View)
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
        fun setOnContinuePressedListener(listener: CabinCustomerRegisterFragment.RegisterFragmentListener)
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
        fun visualizeSex()
        fun selectMan(view: android.view.View)
        fun selectWoman(view: android.view.View)
        fun visualizeSex(view: android.view.View)
        fun switchContinueButton()
        fun setEmailIcon()
        fun setPasswordIcon()
        fun setPasswordConfirmationIcon()
        fun setIcons()
        fun removeFocus()
        fun getInputtedEmail(): String
        fun getInputtedPassword(): String
        fun getInputtedSex(): Int
    }

    interface Interactor : BaseContracts.Interactor {

    }

    interface InteractorOutput : BaseContracts.InteractorOutput {

    }

    interface Router : BaseContracts.Router {
        fun goForward()
    }

}