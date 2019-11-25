package com.cabinInformationTechnologies.cabinCustomerRegistration.fragments.forgotPassword

import android.content.Context

class CabinCustomerForgotPasswordInteractor(var output: CabinCustomerForgotPasswordContracts.InteractorOutput?) :
    CabinCustomerForgotPasswordContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun sendEmail(context: Context, email: String) {
        //TODO
        output?.moveToResetPassword()
    }

    //endregion
}