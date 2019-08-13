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

    override fun moveToHomePage() {
        router?.moveToHomePage()
    }

    override fun moveToFavoritesPage() {
        router?.moveToFavoritesPage()
    }

    override fun moveToCartPage() {
        router?.moveToCartPage()
    }

    override fun moveToDiscoverPage() {
        router?.moveToDiscoverPage()
    }

    //endregion

    //region InteractorOutput

    //TODO: Implement your InteractorOutput methods here

    //endregion
}