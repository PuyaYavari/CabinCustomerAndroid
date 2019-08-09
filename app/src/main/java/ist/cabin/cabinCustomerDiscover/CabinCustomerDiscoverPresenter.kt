package ist.cabin.cabinCustomerDiscover

import android.app.Activity
import android.os.Bundle

class CabinCustomerDiscoverPresenter(var view: CabinCustomerDiscoverContracts.View?) :
    CabinCustomerDiscoverContracts.Presenter, CabinCustomerDiscoverContracts.InteractorOutput {

    var interactor: CabinCustomerDiscoverContracts.Interactor? = CabinCustomerDiscoverInteractor(this)
    var router: CabinCustomerDiscoverContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerDiscoverRouter(activity)

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

    override fun moveToOrdersPage() {
        router?.moveToOrdersPage()
    }

    override fun moveToFavoritesPage() {
        router?.moveToFavoritesPage()
    }

    override fun moveToHomePage() {
        router?.moveToHomePage()
    }

    override fun moveToCartPage() {
        router?.moveToCartPage()
    }

    //endregion

    //region InteractorOutput

    //TODO: Implement your InteractorOutput methods here

    //endregion
}