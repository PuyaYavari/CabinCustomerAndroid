package com.cabinInformationTechnologies.cabinCustomerMeasure.fragments.selectMethod

import android.app.Activity
import android.os.Bundle

class CabinCustomerMeasureSelectMethodPresenter(var view: com.cabinInformationTechnologies.cabinCustomerMeasure.fragments.selectMethod.CabinCustomerMeasureSelectMethodContracts.View?) :
    com.cabinInformationTechnologies.cabinCustomerMeasure.fragments.selectMethod.CabinCustomerMeasureSelectMethodContracts.Presenter,
    com.cabinInformationTechnologies.cabinCustomerMeasure.fragments.selectMethod.CabinCustomerMeasureSelectMethodContracts.InteractorOutput {

    var interactor: com.cabinInformationTechnologies.cabinCustomerMeasure.fragments.selectMethod.CabinCustomerMeasureSelectMethodContracts.Interactor? =
        com.cabinInformationTechnologies.cabinCustomerMeasure.fragments.selectMethod.CabinCustomerMeasureSelectMethodInteractor(
            this
        )
    var router: com.cabinInformationTechnologies.cabinCustomerMeasure.fragments.selectMethod.CabinCustomerMeasureSelectMethodContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router =
            com.cabinInformationTechnologies.cabinCustomerMeasure.fragments.selectMethod.CabinCustomerMeasureSelectMethodRouter(
                activity
            )

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

    override fun moveToAutoMeasureTurorial() {
        router?.moveToAutoMeasureTurorial()
    }
    //endregion

    //region InteractorOutput

    //TODO: Implement your InteractorOutput methods here

    //endregion
}