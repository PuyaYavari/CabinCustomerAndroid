package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.changePassword

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.Constants
import com.cabinInformationTechnologies.cabinCustomerBase.Informer

class CabinCustomerChangePasswordPresenter(var view: CabinCustomerChangePasswordContracts.View?) :
    CabinCustomerChangePasswordContracts.Presenter, CabinCustomerChangePasswordContracts.InteractorOutput {

    var interactor: CabinCustomerChangePasswordContracts.Interactor? =
        CabinCustomerChangePasswordInteractor(
            this
        )
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
        router = CabinCustomerChangePasswordRouter(
                activity
            )
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
        }
        validatePage()
    }

    override fun setNewPassword(inputtedPassword: String) {
        if (inputtedPassword.length <= Constants.MAX_PASSWORD_LENGTH) {
            this.newPassword = inputtedPassword
            newPasswordFilled = inputtedPassword.isNotEmpty()
        } else {
            newPasswordFilled = false
        }
        validatePage()
    }

    override fun setNewPasswordConfirmation(inputtedPassword: String) {
        if (inputtedPassword.length <= Constants.MAX_PASSWORD_LENGTH) {
            this.newPasswordConfirmation = inputtedPassword
            newPasswordConfirmationFilled = inputtedPassword.isNotEmpty()
        } else {
            newPasswordConfirmationFilled = false
        }
        validatePage()
    }

    private fun validatePage() {
        if (passwordFilled && newPasswordFilled && newPasswordConfirmationFilled) view!!.activateAddButton()
        else view!!.deactivateAddButton()
    }

    override fun confirmPage(context: Context) {
        val informer: BaseContracts.Feedbacker by lazy { Informer() }
        if (newPassword == newPasswordConfirmation) {
            interactor?.sendPasswordData(context, newPassword, password)
        } else {
            informer.feedback(
                context = context,
                title = context.resources.getString(R.string.attention),
                message = context.resources.getString(R.string.change_password_new_password_confirmation_error),
                neutralText = context.resources.getString(R.string.okay)
            )
        }
    }

    //endregion

    //region InteractorOutput

    override fun success() {
        view?.success()
    }

    //endregion
}