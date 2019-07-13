package ist.cabin.cabinCustomerLogin

import android.app.Activity
import android.os.Bundle

class CabinCustomerLoginPresenter(var view: CabinCustomerLoginContracts.View?) : CabinCustomerLoginContracts.Presenter,
    CabinCustomerLoginContracts.InteractorOutput {

    var interactor: CabinCustomerLoginContracts.Interactor? = CabinCustomerLoginInteractor(this)
    var router: CabinCustomerLoginContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerLoginRouter(activity)

        bundle?.let {
            //you can delete this if there's no need to get extras from the intent
            //TODO: Do something
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

    override fun login() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //TODO: Implement your InteractorOutput methods here

    //endregion
}