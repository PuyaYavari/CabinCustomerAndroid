package ist.cabin.cabinCustomerRegister

import android.app.Activity
import android.os.Bundle

class CabinCustomerRegisterPresenter(var view: CabinCustomerRegisterContracts.View?) :
    CabinCustomerRegisterContracts.Presenter, CabinCustomerRegisterContracts.InteractorOutput {
    var interactor: CabinCustomerRegisterContracts.Interactor? = CabinCustomerRegisterInteractor(this)
    var router: CabinCustomerRegisterContracts.Router? = null

    var email: String = ""
    var password: String = ""
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


    override fun continueToAgreement() {
        view?.showAgreement()
    }

    override fun backToRegisteration() {
        view?.showRegisteration()
    }

    override fun setFinalEmail(email: String) {
        this.email = email
    }

    override fun setFinalPassword(password: String) {
        this.password = password
    }

    override fun setFinalSex(sex: Int) {
        this.sex = sex
    }

    override fun getFinalEmail(): String {
        return this.email
    }

    override fun getFinalPassword(): String {
        return this.password
    }

    override fun getFinalSex(): Int {
        return this.sex
    }

    //endregion

    //region InteractorOutput

    //endregion
}