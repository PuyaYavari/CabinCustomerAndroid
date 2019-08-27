package ist.cabin.cabincustomer.fragments.ordersDetail

import android.app.Activity
import android.os.Bundle
import ist.cabin.cabincustomer.fragments.orders.PagesIDs

class CabinCustomerOrdersDetailPresenter(var view: CabinCustomerOrdersDetailContracts.View?) :
    CabinCustomerOrdersDetailContracts.Presenter, CabinCustomerOrdersDetailContracts.InteractorOutput {

    var interactor: CabinCustomerOrdersDetailContracts.Interactor? = CabinCustomerOrdersDetailInteractor(this)
    var router: CabinCustomerOrdersDetailContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerOrdersDetailRouter(activity)

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

    override fun setupPropperPage(pageID: Int) {
        if (pageID == PagesIDs.PENDING_PAGE) {
            view!!.setupPendingPage()
        } else if (pageID == PagesIDs.SHIPPING_PAGE) {
            view!!.setupShippingPage()
        } else {
            view!!.setupSentPage()
        }
    }

    //endregion

    //region InteractorOutput

    //endregion
}