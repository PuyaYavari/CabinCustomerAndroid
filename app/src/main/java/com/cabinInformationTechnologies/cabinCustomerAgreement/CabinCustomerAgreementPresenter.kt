package com.cabinInformationTechnologies.cabinCustomerAgreement

import android.app.Activity
import android.os.Bundle

class CabinCustomerAgreementPresenter(var view: com.cabinInformationTechnologies.cabinCustomerAgreement.CabinCustomerAgreementContracts.View?) :
    com.cabinInformationTechnologies.cabinCustomerAgreement.CabinCustomerAgreementContracts.Presenter,
    com.cabinInformationTechnologies.cabinCustomerAgreement.CabinCustomerAgreementContracts.InteractorOutput {

    var interactor: com.cabinInformationTechnologies.cabinCustomerAgreement.CabinCustomerAgreementContracts.Interactor? =
        com.cabinInformationTechnologies.cabinCustomerAgreement.CabinCustomerAgreementInteractor(this)
    var router: com.cabinInformationTechnologies.cabinCustomerAgreement.CabinCustomerAgreementContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = com.cabinInformationTechnologies.cabinCustomerAgreement.CabinCustomerAgreementRouter(activity)

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