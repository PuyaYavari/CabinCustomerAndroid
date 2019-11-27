package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.changePassword

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts

object CabinCustomerChangePasswordContracts {

    interface View : BaseContracts.View {
        fun onBackPressed()
        fun activateAddButton()
        fun deactivateAddButton()
        fun success()
    }

    interface Presenter : BaseContracts.Presenter {
        fun setPassword(inputtedPassword: String)
        fun setNewPassword(inputtedPassword: String)
        fun setNewPasswordConfirmation(inputtedPassword: String)
        fun confirmPage(context: Context)
    }

    interface Interactor : BaseContracts.Interactor {
        fun sendPasswordData(
            context: Context,
            newPassword: String,
            password: String
        )
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        fun success()
    }

    interface Router : BaseContracts.Router

}