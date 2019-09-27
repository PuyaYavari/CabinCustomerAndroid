package ist.cabin.cabincustomer.fragments.orders

import android.app.Activity
import android.os.Bundle

class CabinCustomerOrdersPresenter(var view: CabinCustomerOrdersContracts.View?) :
    CabinCustomerOrdersContracts.Presenter,
    CabinCustomerOrdersContracts.InteractorOutput {

    var interactor: CabinCustomerOrdersContracts.Interactor? =
        CabinCustomerOrdersInteractor(this)
    var router: CabinCustomerOrdersContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerOrdersRouter(activity)

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

    //endregion

    //region InteractorOutput

    //endregion
}