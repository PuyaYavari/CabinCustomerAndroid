package com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition

import android.app.Activity
import android.os.Bundle

class CabinCustomerAddExtraditionPresenter(var view: com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.CabinCustomerAddExtraditionContracts.View?) :
    com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.CabinCustomerAddExtraditionContracts.Presenter,
    com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.CabinCustomerAddExtraditionContracts.InteractorOutput {

    var interactor: com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.CabinCustomerAddExtraditionContracts.Interactor? =
        com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.CabinCustomerAddExtraditionInteractor(
            this
        )
    var router: com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.CabinCustomerAddExtraditionContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router =
            com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.CabinCustomerAddExtraditionRouter(
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

    override fun saveData() {
        interactor?.saveData()
    }

    override fun moveToCongratulationsPage() {
        router?.moveToCongratulationsPage()
    }

    //endregion

    //region InteractorOutput

    //TODO: Implement your InteractorOutput methods here

    //endregion
}