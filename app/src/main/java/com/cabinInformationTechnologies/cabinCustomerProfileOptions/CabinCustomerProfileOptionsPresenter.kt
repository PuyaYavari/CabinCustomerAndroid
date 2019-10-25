package com.cabinInformationTechnologies.cabinCustomerProfileOptions

import android.app.Activity
import android.os.Bundle

class CabinCustomerProfileOptionsPresenter(var view: com.cabinInformationTechnologies.cabinCustomerProfileOptions.CabinCustomerProfileOptionsContracts.View?) :
    com.cabinInformationTechnologies.cabinCustomerProfileOptions.CabinCustomerProfileOptionsContracts.Presenter,
    com.cabinInformationTechnologies.cabinCustomerProfileOptions.CabinCustomerProfileOptionsContracts.InteractorOutput {

    var interactor: com.cabinInformationTechnologies.cabinCustomerProfileOptions.CabinCustomerProfileOptionsContracts.Interactor? =
        com.cabinInformationTechnologies.cabinCustomerProfileOptions.CabinCustomerProfileOptionsInteractor(this)
    var router: com.cabinInformationTechnologies.cabinCustomerProfileOptions.CabinCustomerProfileOptionsContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = com.cabinInformationTechnologies.cabinCustomerProfileOptions.CabinCustomerProfileOptionsRouter(activity)

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

    //endregion

    //region InteractorOutput

    //endregion
}