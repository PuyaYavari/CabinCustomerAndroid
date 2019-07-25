package ist.cabin.cabinCustomerHome.fragments.basket

import android.app.Activity
import android.os.Bundle

class CabinCustomerHomeBasketFragmentPresenter(var view: CabinCustomerHomeBasketFragmentContracts.View?) :
    CabinCustomerHomeBasketFragmentContracts.Presenter, CabinCustomerHomeBasketFragmentContracts.InteractorOutput {

    var interactor: CabinCustomerHomeBasketFragmentContracts.Interactor? =
        CabinCustomerHomeBasketFragmentInteractor(this)
    var router: CabinCustomerHomeBasketFragmentContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerHomeBasketFragmentRouter(activity)

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

    //TODO: Implement your Presenter methods here

    //endregion

    //region InteractorOutput

    //TODO: Implement your InteractorOutput methods here

    //endregion
}