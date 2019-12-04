package com.cabinInformationTechnologies.cabinCustomerRegistration.fragments.resetPassword

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.Informer

class CabinCustomerResetPasswordPresenter(var view: CabinCustomerResetPasswordContracts.View?) :
    CabinCustomerResetPasswordContracts.Presenter,
    CabinCustomerResetPasswordContracts.InteractorOutput {

    private val informer: BaseContracts.Feedbacker by lazy {
        Informer()
    }

    var interactor: CabinCustomerResetPasswordContracts.Interactor? =
        CabinCustomerResetPasswordInteractor(this)
    var router: CabinCustomerResetPasswordContracts.Router? = null

    override var email: String? = null
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
            val email = this.email
            if (context != null && email != null)
                interactor?.sendPasswordToBackend(context, password, code, email)
        } else {
            if (context != null)
                informer.feedback(context, context.resources.getString(R.string.password_confirmation_error))
        }
    }

    //endregion

    //region InteractorOutput

    override fun success(context: Context) {
        informer.feedback(
            context = context,
            title = context.resources.getString(R.string.congratulations),
            message = context.resources.getString(R.string.password_changed_successfully),
            positiveText = context.resources.getString(R.string.okay),
            positiveFunction = { view?.success() },
            negativeText = null,
            negativeFunction = null,
            neutralText = null,
            neutralFunction = null
        )
    }

    //endregion
}