package ist.cabin.cabinCustomerRegister

import android.app.Activity
import android.os.Bundle
import android.util.Log

class CabinCustomerRegisterPresenter(var view: CabinCustomerRegisterContracts.View?) :
    CabinCustomerRegisterContracts.Presenter, CabinCustomerRegisterContracts.InteractorOutput {

    var interactor: CabinCustomerRegisterContracts.Interactor? = CabinCustomerRegisterInteractor(this)
    var router: CabinCustomerRegisterContracts.Router? = null

    var email: String = ""
    var password: String = ""
    var passwordConfirmation = ""
    var sex = -1 // 0: woman, 1: man

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerRegisterRouter(activity)

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

    override fun isPasswordValid(password: String): Boolean {
        val pattern = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,}$".toRegex()
        Log.d("Password is", password)
        Log.d("is valid", pattern.matches(password).toString())
        return (pattern.matches(password))
    }

    override fun setInputtedEmail(emailInput: String) {
        email = emailInput
    }

    override fun setInputtedPassword(passwordInput: String) {
        password = passwordInput
    }

    override fun setInputtedPasswordConfirmation(passwordConfirmationInput: String) {
        passwordConfirmation = passwordConfirmationInput
    }

    override fun arePasswordsSame(): Boolean {
        return(password == passwordConfirmation)
    }

    override fun selectMan() {
        sex = 1
        view?.unselectWoman()
        view?.selectMan()
    }

    override fun selectWoman() {
        sex = 0
        view?.unselectMan()
        view?.selectWoman()
    }

    override fun switchContinueButton() {
        if (isEmailValid(email) && isPasswordValid(password) && arePasswordsSame() && sex != -1) {
            view?.enableContinueButton()
        } else {
            view?.disableContinueButton()
        }
    }

    override fun continueToAgreement() {
        router?.moveToAgreementPage()
    }

    //endregion

    //region InteractorOutput

    //endregion
}