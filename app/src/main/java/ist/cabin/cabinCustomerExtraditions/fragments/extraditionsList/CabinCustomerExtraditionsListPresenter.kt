package ist.cabin.cabinCustomerExtraditions.fragments.extraditionsList

import android.app.Activity
import android.os.Bundle

class CabinCustomerExtraditionsListPresenter(var view: CabinCustomerExtraditionsListContracts.View?) :
    CabinCustomerExtraditionsListContracts.Presenter,
    CabinCustomerExtraditionsListContracts.InteractorOutput {

    var interactor: CabinCustomerExtraditionsListContracts.Interactor? =
        CabinCustomerExtraditionsListInteractor(this)
    var router: CabinCustomerExtraditionsListContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerExtraditionsListRouter(activity)

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

    override fun addExtradition() {
        router?.moveToAddExtraditionPage()
    }

    override fun setupPage() { //TODO: change based on correct data
        if (interactor?.getInitialData() == null) {
            //view!!.setupNoExtraditionList()
            view!!.setupExtraditionsList()
        } else {
            view!!.setupExtraditionsList()
        }
    }

    override fun moveToExtraditionDetail() {
        router?.moveToExtraditionDetail()
    }

    override fun moveToExtraditionDetailAccepted() {
        router?.moveToExtraditionDetailAccepted()
    }

    override fun moveToExtraditionDetailDenied() {
        router?.moveToExtraditionDetailDenied()
    }

    //endregion

    //region InteractorOutput

    //TODO: Implement your InteractorOutput methods here

    //endregion
}