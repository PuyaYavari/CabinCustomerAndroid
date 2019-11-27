package com.cabinInformationTechnologies.cabinCustomerProfileOptions

import android.app.Activity
import android.os.Bundle

class CabinCustomerProfileOptionsPresenter(var view: CabinCustomerProfileOptionsContracts.View?) :
    CabinCustomerProfileOptionsContracts.Presenter,
    CabinCustomerProfileOptionsContracts.InteractorOutput {

    var interactor: CabinCustomerProfileOptionsContracts.Interactor? =
        CabinCustomerProfileOptionsInteractor(this)
    var router: CabinCustomerProfileOptionsContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerProfileOptionsRouter(activity)
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