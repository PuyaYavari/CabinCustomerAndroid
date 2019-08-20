package ist.cabin.cabinCustomerProfileOptions.fragments.personalDataOptions

import android.app.Activity
import android.os.Bundle

class CabinCustomerPersonalDataOptionsPresenter(var view: CabinCustomerPersonalDataOptionsContracts.View?) :
    CabinCustomerPersonalDataOptionsContracts.Presenter, CabinCustomerPersonalDataOptionsContracts.InteractorOutput {

    var interactor: CabinCustomerPersonalDataOptionsContracts.Interactor? =
        CabinCustomerPersonalDataOptionsInteractor(this)
    var router: CabinCustomerPersonalDataOptionsContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerPersonalDataOptionsRouter(activity)

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