package com.cabinInformationTechnologies.cabinCustomerRegistration.fragments.loginRegister

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task

object CabinCustomerLoginRegisterContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        fun enableLoginButton()
        fun disableLoginbutton()
        fun enableRegisterButton()
        fun disableRegisterButton()
        fun selectWoman()
        fun selectMan()
        fun closeActivity()
        fun setActiveUser(user: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELUser)
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        fun isEmailValid(email: String): Boolean
        fun setInputtedEmail(emailInput: String)
        fun setInputtedPassword(password: String)
        fun setInputtedPasswordConfirmation(password: String)
        fun setSex(sex: Int)
        fun setEmailPermit(permit: Boolean)
        fun switchRegisterButton()
        fun switchLoginButton()
        fun login()
        fun register()
        fun forgetPassword()
        fun handleGoogleSignInResult(context: Context?, completedTask: Task<GoogleSignInAccount>)
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {
        fun login(context: Context ,email: String, password: String)
        fun login(context: Context, account: GoogleSignInAccount)
        fun register(context: Context ,email: String, password: String, sex: Int, emailPermit: Boolean)
    }

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput {
        fun setActiveUser(user: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELUser)
        fun closeActivity()
        fun sendLoginRequest()
    }

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router

}