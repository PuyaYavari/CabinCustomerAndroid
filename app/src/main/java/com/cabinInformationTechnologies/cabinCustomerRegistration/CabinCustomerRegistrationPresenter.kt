package com.cabinInformationTechnologies.cabinCustomerRegistration

import android.app.Activity
import android.os.Bundle

class CabinCustomerRegistrationPresenter(var view: CabinCustomerRegistrationContracts.View?) :
    CabinCustomerRegistrationContracts.Presenter,
    CabinCustomerRegistrationContracts.InteractorOutput {

    var interactor: CabinCustomerRegistrationContracts.Interactor? = CabinCustomerRegistrationInteractor(this)
    var router: CabinCustomerRegistrationContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerRegistrationRouter(activity)
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

    //endregion

    //region InteractorOutput

    //endregion
}