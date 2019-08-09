package ist.cabin.cabinCustomerForgotpassword

import android.app.Activity
import android.os.Bundle

class CabinCustomerForgotpasswordPresenter(var view: CabinCustomerForgotpasswordContracts.View?) :
    CabinCustomerForgotpasswordContracts.Presenter, CabinCustomerForgotpasswordContracts.InteractorOutput {

    var interactor: CabinCustomerForgotpasswordContracts.Interactor? = CabinCustomerForgotpasswordInteractor(this)
    var router: CabinCustomerForgotpasswordContracts.Router? = null

    var email: String = ""

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerForgotpasswordRouter(activity)

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

    override fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun setInputtedEmail(emailInput: String) {
        email = emailInput
    }

    override fun switchConfirmationButton() {
        if(isEmailValid(email)) {
            view?.enableConfirmationButton()
        } else {
            view?.disableConfirmationButton()
        }
    }

    override fun confirm() {
        router?.moveToConfirmationPage()
    }

    //endregion

    //region InteractorOutput

    //endregion
}