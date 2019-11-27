package com.cabinInformationTechnologies.cabinCustomerRegistration.fragments.loginRegister

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELUser
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task

object CabinCustomerLoginRegisterContracts {

    interface View : BaseContracts.View {
        fun enableLoginButton()
        fun disableLoginbutton()
        fun enableRegisterButton()
        fun disableRegisterButton()
        fun selectWoman()
        fun selectMan()
        fun closeActivity()
        fun setActiveUser(user: MODELUser)
    }

    interface Presenter : BaseContracts.Presenter {
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

    interface Interactor : BaseContracts.Interactor {
        fun login(context: Context ,email: String, password: String)
        fun login(context: Context, account: GoogleSignInAccount)
        fun register(context: Context ,email: String, password: String, sex: Int, emailPermit: Boolean)
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        fun setActiveUser(user: MODELUser)
        fun closeActivity()
        fun sendLoginRequest()
    }

    interface Router : BaseContracts.Router {
        fun moveToForgotPassword()
    }

}