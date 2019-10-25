package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.changePassword

import android.content.Context

object CabinCustomerChangePasswordContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        fun onBackPressed()
        fun activateAddButton()
        fun deactivateAddButton()
        fun showErrorMessage(title: String, message: String)
        fun showErrorMessage(errorField: Int)
        fun showSuccessMessage()
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        fun setPassword(inputtedPassword: String)
        fun setNewPassword(inputtedPassword: String)
        fun setNewPasswordConfirmation(inputtedPassword: String)
        fun confirmPage(context: Context)
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {
        fun sendPasswordData(
            context: Context,
            newPassword: String,
            password: String
        )
    }

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router

}