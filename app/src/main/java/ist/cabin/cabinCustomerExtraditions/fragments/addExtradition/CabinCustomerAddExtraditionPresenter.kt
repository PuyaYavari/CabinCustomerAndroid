package ist.cabin.cabinCustomerExtraditions.fragments.addExtradition

import android.app.Activity
import android.os.Bundle

class CabinCustomerAddExtraditionPresenter(var view: CabinCustomerAddExtraditionContracts.View?) :
    CabinCustomerAddExtraditionContracts.Presenter,
    CabinCustomerAddExtraditionContracts.InteractorOutput {

    var interactor: CabinCustomerAddExtraditionContracts.Interactor? =
        CabinCustomerAddExtraditionInteractor(this)
    var router: CabinCustomerAddExtraditionContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerAddExtraditionRouter(activity)

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

    override fun saveData() {
        interactor?.saveData()
    }

    override fun moveToCongratulationsPage() {
        router?.moveToCongratulationsPage()
    }

    //endregion

    //region InteractorOutput

    //TODO: Implement your InteractorOutput methods here

    //endregion
}