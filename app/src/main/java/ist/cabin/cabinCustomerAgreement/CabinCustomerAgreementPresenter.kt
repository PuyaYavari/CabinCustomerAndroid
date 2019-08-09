package ist.cabin.cabinCustomerAgreement

import android.app.Activity
import android.os.Bundle

class CabinCustomerAgreementPresenter(var view: CabinCustomerAgreementContracts.View?) :
    CabinCustomerAgreementContracts.Presenter, CabinCustomerAgreementContracts.InteractorOutput {

    var interactor: CabinCustomerAgreementContracts.Interactor? = CabinCustomerAgreementInteractor(this)
    var router: CabinCustomerAgreementContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerAgreementRouter(activity)

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

    override fun goBack() {
        router?.goBack()
    }

    override fun accept() {
        router?.goForward()
    }

    //endregion

    //region InteractorOutput

    //endregion
}