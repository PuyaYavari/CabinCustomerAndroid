package ist.cabin.cabinCustomerRoot

import android.app.Activity
import android.os.Bundle

class CabinCustomerRootPresenter(var view: CabinCustomerRootContracts.View?) : CabinCustomerRootContracts.Presenter,
    CabinCustomerRootContracts.InteractorOutput {

    var interactor: CabinCustomerRootContracts.Interactor? = CabinCustomerRootInteractor(this)
    var router: CabinCustomerRootContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerRootRouter(activity)
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

    override fun moveToLoginPage() {
        router?.moveToLoginPage()
    }

    override fun moveToRegisterPage() {
        router?.moveToRegisterPage()
    }

    override fun moveToInfoPage() {
        router?.moveToInfoPage()
    }

    //endregion

    //region InteractorOutput

    //endregion
}