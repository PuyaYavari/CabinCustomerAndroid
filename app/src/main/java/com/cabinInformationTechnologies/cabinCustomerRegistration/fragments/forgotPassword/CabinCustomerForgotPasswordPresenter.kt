package com.cabinInformationTechnologies.cabinCustomerRegistration.fragments.forgotPassword

import android.app.Activity
import android.content.Context
import android.os.Bundle

class CabinCustomerForgotPasswordPresenter(var view: CabinCustomerForgotPasswordContracts.View?) :
    CabinCustomerForgotPasswordContracts.Presenter,
    CabinCustomerForgotPasswordContracts.InteractorOutput {

    var interactor: CabinCustomerForgotPasswordContracts.Interactor? =
        CabinCustomerForgotPasswordInteractor(this)
    var router: CabinCustomerForgotPasswordContracts.Router? = null

    override var email: String = ""
        set(value) {
            field = value
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
                view?.enableButton()
            else
                view?.disableButton()
        }

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerForgotPasswordRouter(activity)
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

    override fun sendEmail(context: Context?) {
        if (context != null)
            interactor?.sendEmail(context, email)
    }

    //endregion

    //region InteractorOutput

    override fun moveToResetPassword() {
        router?.moveToResetPassword()
    }

    //endregion
}