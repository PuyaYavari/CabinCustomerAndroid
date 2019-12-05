package com.cabinInformationTechnologies.cabinCustomerRegistration.fragments.resetPassword

import android.content.Context
import android.provider.ContactsContract
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts

object CabinCustomerResetPasswordContracts {

    interface View : BaseContracts.View {
        fun enableButton()
        fun disableButton()
        fun success()
    }

    interface Presenter : BaseContracts.Presenter {
        var password: String
        var passwordConfirmation: String
        var code: String
        var email: String?

        fun buttonListener(context: Context?)
    }

    interface Interactor : BaseContracts.Interactor {
        fun sendPasswordToBackend(context: Context, password: String, code: String, email: String)
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        fun success(context: Context)
    }

    interface Router : BaseContracts.Router {
        //TODO
    }

}