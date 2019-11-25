package com.cabinInformationTechnologies.cabinCustomerRegistration.fragments.resetPassword

import android.app.Activity
import android.content.Context
import android.os.Bundle

class CabinCustomerResetPasswordPresenter(var view: CabinCustomerResetPasswordContracts.View?) :
    CabinCustomerResetPasswordContracts.Presenter,
    CabinCustomerResetPasswordContracts.InteractorOutput {

    var interactor: CabinCustomerResetPasswordContracts.Interactor? =
        CabinCustomerResetPasswordInteractor(this)
    var router: CabinCustomerResetPasswordContracts.Router? = null

    override var password: String = ""
        set(value) {
            field = value
            passwordFilled = value != ""
            checkPage()
        }
    override var passwordConfirmation: String = ""
        set(value) {
            field = value
            passwordConfirmationFilled = value != ""
            checkPage()
        }
    override var code: String = ""
        set(value) {
            field = value
            codeFilled = value != ""
            checkPage()
        }

    private var passwordFilled: Boolean = false
    private var passwordConfirmationFilled: Boolean = false
    private var codeFilled: Boolean = false

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerResetPasswordRouter(activity)
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

    private fun checkPage() {
        if (passwordFilled && passwordConfirmationFilled && codeFilled)
            view?.enableButton()
        else
            view?.disableButton()
    }

    override fun buttonListener(context: Context?) {
        if (password == passwordConfirmation) {
            if (context != null)
                interactor?.sendPasswordToBackend(context, password, code)
        } else {
            //TODO: FEEDBACK
        }
    }

    //endregion

    //region InteractorOutput

    //TODO: Implement your InteractorOutput methods here

    //endregion
}