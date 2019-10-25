package com.cabinInformationTechnologies.cabinCustomerAgreement.fragments.register

object CabinCustomerRegisterFragmentContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
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
        fun setOnContinuePressedListener(listener: com.cabinInformationTechnologies.cabinCustomerAgreement.fragments.register.CabinCustomerRegisterFragment.RegisterFragmentListener)
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
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

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {

    }

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput {

    }

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router {
        fun goForward()
    }

}