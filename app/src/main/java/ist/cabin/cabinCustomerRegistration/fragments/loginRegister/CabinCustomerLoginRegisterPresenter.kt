package ist.cabin.cabinCustomerRegistration.fragments.loginRegister

import android.app.Activity
import android.os.Bundle
import ist.cabin.cabinCustomerBase.GlobalData
import ist.cabin.cabinCustomerBase.models.local.MODELUser

class CabinCustomerLoginRegisterPresenter(var view: CabinCustomerLoginRegisterContracts.View?) : CabinCustomerLoginRegisterContracts.Presenter,
    CabinCustomerLoginRegisterContracts.InteractorOutput {

    var interactor: CabinCustomerLoginRegisterContracts.Interactor? =
        CabinCustomerLoginRegisterInteractor(this)
    var router: CabinCustomerLoginRegisterContracts.Router? = null

    private var email: String = ""
    private var password: String = ""
    private var passwordConfirmation = ""
    private var sex = 0
    private var emailPermit = false

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerLoginRegisterRouter(activity)

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

    private fun confirmPassword(): Boolean {
        return password == passwordConfirmation
    }

    override fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun setInputtedEmail(emailInput: String) {
        email = emailInput
    }

    override fun setInputtedPassword(passwordInput: String) {
        password = passwordInput
    }

    override fun switchLoginButton() {
        if (isEmailValid(email) && password.isNotEmpty()) {
            view?.enableLoginButton()
        } else {
            view?.disableLoginbutton()
        }
    }

    override fun login() {
        val context = view?.getActivityContext()
        if (context != null)
            interactor?.login(context ,email, password)
        //TODO: ELSE
    }

    override fun forgetPassword() {
        //TODO
    }

    override fun setInputtedPasswordConfirmation(password: String) {
        passwordConfirmation = password
    }

    override fun switchRegisterButton() {
        if (isEmailValid(email) && confirmPassword()) {
            view?.enableRegisterButton()
        } else {
            view?.disableRegisterButton()
        }
    }

    override fun setEmailPermit(permit: Boolean) {
        emailPermit = permit
    }

    override fun setSex(sex: Int) {
        this.sex = sex
    }

    override fun register() {
        val context = view?.getActivityContext()
        if (context != null)
            interactor?.register(context ,email, password, sex, emailPermit)
        //TODO: ELSE
    }

    override fun toRegister() {

    }

    override fun toLogin() {

    }

    //endregion

    override fun setActiveUser(user: MODELUser) {
        GlobalData.activeUser = user
    }
}