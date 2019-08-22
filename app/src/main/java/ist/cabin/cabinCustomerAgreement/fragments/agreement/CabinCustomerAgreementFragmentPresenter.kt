package ist.cabin.cabinCustomerAgreement.fragments.agreement

import android.app.Activity
import android.os.Bundle

class CabinCustomerAgreementFragmentPresenter(var view: CabinCustomerAgreementFragmentContracts.View?) :
    CabinCustomerAgreementFragmentContracts.Presenter,
    CabinCustomerAgreementFragmentContracts.InteractorOutput {

    var interactor: CabinCustomerAgreementFragmentContracts.Interactor? =
        CabinCustomerAgreementFragmentInteractor(
            this
        )
    var router: CabinCustomerAgreementFragmentContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router =
            CabinCustomerAgreementFragmentRouter(
                activity
            )

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
        //router?.goBack()
    }

    override fun accept() {
        router?.goForward()
    }

    //endregion

    //region InteractorOutput


    //endregion
}