package ist.cabin.cabinCustomerLogin

import android.app.Activity
import android.os.Bundle
import android.util.Log

class CabinCustomerLoginPresenter(var view: CabinCustomerLoginContracts.View?) : CabinCustomerLoginContracts.Presenter,
    CabinCustomerLoginContracts.InteractorOutput {

    var interactor: CabinCustomerLoginContracts.Interactor? = CabinCustomerLoginInteractor(this)
    var router: CabinCustomerLoginContracts.Router? = null

    var email: String = ""
    var password: String = ""

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerLoginRouter(activity)

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
        Log.d("email set to",emailInput)
        email = emailInput
    }

    override fun setInputtedPassword(passwordInput: String) {
        Log.d("password set to",passwordInput)
        password = passwordInput
    }

    override fun switchLoginButton() {
        Log.d("email", email)
        Log.d("password", password)


        if (isEmailValid(email) && password.isNotEmpty()) {
            view?.enableLoginButton()
        } else {
            view?.disableLoginbutton()
        }
    }

    override fun login() {
        Log.d("login button", "pressed")
        interactor?.login(email, password)
        //TODO: check if data valid
        router?.moveToHomePage()
    }

    //endregion
}