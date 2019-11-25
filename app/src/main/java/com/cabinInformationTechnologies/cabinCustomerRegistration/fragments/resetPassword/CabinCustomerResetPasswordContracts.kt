package com.cabinInformationTechnologies.cabinCustomerRegistration.fragments.resetPassword

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts

object CabinCustomerResetPasswordContracts {

    interface View : BaseContracts.View {
        fun enableButton()
        fun disableButton()
    }

    interface Presenter : BaseContracts.Presenter {
        var password: String
        var passwordConfirmation: String
        var code: String

        fun buttonListener(context: Context?)
    }

    interface Interactor : BaseContracts.Interactor {
        fun sendPasswordToBackend(context: Context, password: String, code: String)
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        //TODO: FEEDBACK
    }

    interface Router : BaseContracts.Router {
        //TODO
    }

}