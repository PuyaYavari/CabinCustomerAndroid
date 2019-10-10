package ist.cabin.cabinCustomerProfileOptions.fragments.changePassword

import android.content.Context
import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerChangePasswordContracts {

    interface View : BaseContracts.View {
        fun onBackPressed()
        fun activateAddButton()
        fun deactivateAddButton()
        fun showErrorMessage(title: String, message: String)
        fun showErrorMessage(errorField: Int)
        fun showSuccessMessage()
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

    interface InteractorOutput : BaseContracts.InteractorOutput

    interface Router : BaseContracts.Router

}