package ist.cabin.cabinCustomerFinishTrade

import android.app.Activity
import android.os.Bundle

class CabinCustomerFinishTradePresenter(var view: CabinCustomerFinishTradeContracts.View?) :
    CabinCustomerFinishTradeContracts.Presenter,
    CabinCustomerFinishTradeContracts.InteractorOutput {

    var interactor: CabinCustomerFinishTradeContracts.Interactor? =
        CabinCustomerFinishTradeInteractor(this)
    var router: CabinCustomerFinishTradeContracts.Router? = null

    private var priceDetailIsVisible: Boolean = false

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerFinishTradeRouter(activity)

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

    override fun togglePriceDetail() {
        priceDetailIsVisible = if (!priceDetailIsVisible) {
            view!!.showPriceDetail()
            true
        } else {
            view!!.hidePriceDetail()
            false
        }
    }

    //endregion

    //region InteractorOutput

    //TODO: Implement your InteractorOutput methods here

    //endregion
}