package com.cabinInformationTechnologies.cabinCustomerRegistration.fragments.resetPassword

import android.content.Context

class CabinCustomerResetPasswordInteractor(var output: CabinCustomerResetPasswordContracts.InteractorOutput?) :
    CabinCustomerResetPasswordContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun sendPasswordToBackend(context: Context, password: String, code: String) {
        //TODO
    }

    //endregion
}