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

    var focus = -1// -1: none, 0: email, 1: password, 2: passwordConfirmation
    var emailHasIcon = false
    var passwordHasIcon = false
    var passwordConfirmationHasIcon = false

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
        focus = 0
        email = emailInput
    }

    override fun setInputtedPassword(passwordInput: String) {
        focus = 1
        password = passwordInput
    }

    override fun setInputtedPasswordConfirmation(passwordConfirmationInput: String) {
        focus = 2
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

    override fun setEmailIcon() {
        if (email != "") {
            if (isEmailValid(email)) {
                view?.emailStatusTickIcon()
            } else {
                if (focus == 0) {
                    view?.emailStatusEditIcon()
                } else {
                    view?.emailStatusCrossIcon()
                }
            }
            emailHasIcon = true
        } else {
            view?.emailStatusRemoveIcon()
            emailHasIcon = false
        }
    }

    override fun setPasswordIcon() {
        if (password != "") {
            if (isPasswordValid(password)) {
                view?.passwordStatusTickIcon()
            } else {
                if (focus == 1) {
                    view?.passwordStatusEditIcon()
                } else {
                    view?.passwordStatusCrossIcon()
                }
            }
            passwordHasIcon = true
        } else {
            view?.passwordStatusRemoveIcon()
            passwordHasIcon = false
        }
    }

    override fun setPasswordConfirmationIcon() {
        if (passwordConfirmation != "") {
            if (arePasswordsSame()) {
                view?.passwordConfirmationStatusTickIcon()
            } else {
                if (focus == 2) {
                    view?.passwordConfirmationStatusEditIcon()
                } else {
                    view?.passwordConfirmationStatusCrossIcon()
                }
            }
            passwordConfirmationHasIcon = true
        } else {
            view?.passwordConfirmationStatusRemoveIcon()
            passwordConfirmationHasIcon = false
        }
    }

    override fun setIcons() {
        if (focus == -1) {
            if (emailHasIcon)
                setEmailIcon()
            if (passwordHasIcon)
                setPasswordIcon()
            if (passwordConfirmationHasIcon)
                setPasswordConfirmationIcon()
        } else if (focus == 0) {
            setEmailIcon()
            if (passwordHasIcon)
                setPasswordIcon()
            if (passwordConfirmationHasIcon)
                setPasswordConfirmationIcon()
        } else if (focus == 1) {
            setPasswordIcon()
            if (emailHasIcon)
                setEmailIcon()
            if (passwordConfirmationHasIcon)
                setPasswordConfirmationIcon()
        } else if (focus == 2) {
            setPasswordConfirmationIcon()
            if (emailHasIcon)
                setEmailIcon()
            if (passwordHasIcon)
                setPasswordIcon()
        }
    }

    override fun removeFocus() {
        focus = -1
    }

    //endregion

    //region InteractorOutput

    //endregion
}