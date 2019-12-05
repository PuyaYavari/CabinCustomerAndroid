package com.cabinInformationTechnologies.cabinCustomerRegistration.fragments.forgotPassword

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts

object CabinCustomerForgotPasswordContracts {

    interface View : BaseContracts.View {
        fun enableButton()
        fun disableButton()
    }

    interface Presenter : BaseContracts.Presenter {
        var email: String

        fun sendEmail(context: Context?)
    }

    interface Interactor : BaseContracts.Interactor {
        fun sendEmail(context: Context, email: String)
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        fun moveToResetPassword(email: String)
    }

    interface Router : BaseContracts.Router {
        fun moveToResetPassword(email: String)
    }

}