package ist.cabin.cabincustomer.fragments.cart

import android.app.Activity
import android.os.Bundle

class CabinCustomerCartPresenter(var view: CabinCustomerCartContracts.View?) : CabinCustomerCartContracts.Presenter,
    CabinCustomerCartContracts.InteractorOutput {

    var interactor: CabinCustomerCartContracts.Interactor? =
        CabinCustomerCartInteractor(this)
    var router: CabinCustomerCartContracts.Router? = null

    private var priceDetailIsVisible: Boolean = false

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerCartRouter(activity)

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

    override fun togglePriceDetail() {
        priceDetailIsVisible = if (!priceDetailIsVisible) {
            view!!.showPriceDetail()
            true
        } else {
            view!!.hidePriceDetail()
            false
        }
    }

    override fun moveToFinishTrade() {
        router?.moveToFinishTrade()
    }

    //endregion

    //region InteractorOutput

    //TODO: Implement your InteractorOutput methods here

    //endregion
}