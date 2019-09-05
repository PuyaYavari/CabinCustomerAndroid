package ist.cabin.cabinCustomerExtraditions.fragments.addExtraditionCurrentItemsList

import android.app.Activity
import android.os.Bundle

class CabinCustomerAddExtraditionCurrentItemsListPresenter(var view: CabinCustomerAddExtraditionCurrentItemsListContracts.View?) :
    CabinCustomerAddExtraditionCurrentItemsListContracts.Presenter,
    CabinCustomerAddExtraditionCurrentItemsListContracts.InteractorOutput {

    var interactor: CabinCustomerAddExtraditionCurrentItemsListContracts.Interactor? =
        CabinCustomerAddExtraditionCurrentItemsListInteractor(this)
    var router: CabinCustomerAddExtraditionCurrentItemsListContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerAddExtraditionCurrentItemsListRouter(activity)

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

    override fun moveForward() {
        router?.moveForward() //TODO: SEND DATA
    }

    //endregion

    //region InteractorOutput

    //TODO: Implement your InteractorOutput methods here

    //endregion
}