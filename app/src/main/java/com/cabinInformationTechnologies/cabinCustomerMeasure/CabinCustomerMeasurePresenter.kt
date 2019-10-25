package com.cabinInformationTechnologies.cabinCustomerMeasure

import android.app.Activity
import android.os.Bundle

class CabinCustomerMeasurePresenter(var view: com.cabinInformationTechnologies.cabinCustomerMeasure.CabinCustomerMeasureContracts.View?) :
    com.cabinInformationTechnologies.cabinCustomerMeasure.CabinCustomerMeasureContracts.Presenter,
    com.cabinInformationTechnologies.cabinCustomerMeasure.CabinCustomerMeasureContracts.InteractorOutput {

    var interactor: com.cabinInformationTechnologies.cabinCustomerMeasure.CabinCustomerMeasureContracts.Interactor? =
        com.cabinInformationTechnologies.cabinCustomerMeasure.CabinCustomerMeasureInteractor(this)
    var router: com.cabinInformationTechnologies.cabinCustomerMeasure.CabinCustomerMeasureContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = com.cabinInformationTechnologies.cabinCustomerMeasure.CabinCustomerMeasureRouter(activity)

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