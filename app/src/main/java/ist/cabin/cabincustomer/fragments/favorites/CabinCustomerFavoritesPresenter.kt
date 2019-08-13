package ist.cabin.cabincustomer.fragments.favorites

import android.app.Activity
import android.os.Bundle

class CabinCustomerFavoritesPresenter(var view: CabinCustomerFavoritesContracts.View?) :
    CabinCustomerFavoritesContracts.Presenter,
    CabinCustomerFavoritesContracts.InteractorOutput {

    var interactor: CabinCustomerFavoritesContracts.Interactor? =
        CabinCustomerFavoritesInteractor(this)
    var router: CabinCustomerFavoritesContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerFavoritesRouter(activity)

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

    override fun moveToHomePage() {
        router?.moveToHomePage()
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