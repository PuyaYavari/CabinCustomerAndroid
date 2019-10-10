package ist.cabin.cabinCustomerProfileOptions.fragments.changePassword

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import ist.cabin.cabinCustomerBase.Constants

class CabinCustomerChangePasswordPresenter(var view: CabinCustomerChangePasswordContracts.View?) :
    CabinCustomerChangePasswordContracts.Presenter, CabinCustomerChangePasswordContracts.InteractorOutput {

    var interactor: CabinCustomerChangePasswordContracts.Interactor? = CabinCustomerChangePasswordInteractor(this)
    var router: CabinCustomerChangePasswordContracts.Router? = null

    private lateinit var password: String
    private lateinit var newPassword: String
    private lateinit var newPasswordConfirmation: String

    private var passwordFilled: Boolean = false
    private var newPasswordFilled: Boolean = false
    private var newPasswordConfirmationFilled: Boolean = false

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerChangePasswordRouter(activity)

        bundle?.let {
            //you can delete this if there's no need to get extras from the intent
        }
    }

    override fun onDestroy() {
        view = null
        interactor?.unregister()
        interactor = null
        router?.unregister()
        router = null
    }

    //endregion

    //region Presenter

    override fun setPassword(inputtedPassword: String) {
        if (inputtedPassword.length <= Constants.MAX_PASSWORD_LENGTH) {
            this.password = inputtedPassword
            passwordFilled = inputtedPassword.isNotEmpty()
        } else {
            passwordFilled = false
            Log.e("input error", "password too long!")
        }

        validatePage()
    }

    override fun setNewPassword(inputtedPassword: String) {
        if (inputtedPassword.length <= Constants.MAX_PASSWORD_LENGTH) {
            this.newPassword = inputtedPassword
            newPasswordFilled = inputtedPassword.isNotEmpty()
        } else {
            newPasswordFilled = false
            Log.e("input error", "new password too long!")
        }

        validatePage()
    }

    override fun setNewPasswordConfirmation(inputtedPassword: String) {
        if (inputtedPassword.length <= Constants.MAX_PASSWORD_LENGTH) {
            this.newPasswordConfirmation = inputtedPassword
            newPasswordConfirmationFilled = inputtedPassword.isNotEmpty()
        } else {
            newPasswordConfirmationFilled = false
            Log.e("input error", "new password confirmation too long!")
        }

        validatePage()
    }

    private fun isPasswordValid(password: String): Boolean {
        val pattern = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,}$".toRegex()
        return (pattern.matches(password))
    }

    private fun validatePage() {
        if (passwordFilled && newPasswordFilled && newPasswordConfirmationFilled) view!!.activateAddButton()
        else view!!.deactivateAddButton()
    }

    override fun confirmPage(context: Context) {
        if (isPasswordValid(newPassword) && newPassword == newPasswordConfirmation) {
            interactor?.sendPasswordData(context, newPassword, password)
        } else if (!isPasswordValid(newPassword)){
            view!!.showErrorMessage(ChangePasswordFieldIDs.NEW_PASSWORD)
        } else if (newPassword != newPasswordConfirmation) {
            view!!.showErrorMessage(ChangePasswordFieldIDs.NEW_PASSWORD_CONFIRMATION)
        } else {
            view!!.showErrorMessage(-1)
        }
    }

    //TODO: WAIT UNTIL SUCCESS OR FAIL ARRIVES AND SHOW THE MESSAGE

    //endregion

    //region InteractorOutput

    //endregion
}