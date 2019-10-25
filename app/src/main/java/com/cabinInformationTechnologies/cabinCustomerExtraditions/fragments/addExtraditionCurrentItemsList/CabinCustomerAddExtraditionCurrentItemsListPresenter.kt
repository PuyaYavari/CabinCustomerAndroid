package com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtraditionCurrentItemsList

import android.app.Activity
import android.os.Bundle

class CabinCustomerAddExtraditionCurrentItemsListPresenter(var view: com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtraditionCurrentItemsList.CabinCustomerAddExtraditionCurrentItemsListContracts.View?) :
    com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtraditionCurrentItemsList.CabinCustomerAddExtraditionCurrentItemsListContracts.Presenter,
    com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtraditionCurrentItemsList.CabinCustomerAddExtraditionCurrentItemsListContracts.InteractorOutput {

    var interactor: com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtraditionCurrentItemsList.CabinCustomerAddExtraditionCurrentItemsListContracts.Interactor? =
        com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtraditionCurrentItemsList.CabinCustomerAddExtraditionCurrentItemsListInteractor(
            this
        )
    var router: com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtraditionCurrentItemsList.CabinCustomerAddExtraditionCurrentItemsListContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router =
            com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtraditionCurrentItemsList.CabinCustomerAddExtraditionCurrentItemsListRouter(
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

    override fun moveForward() {
        router?.moveForward() //TODO: SEND DATA
    }

    //endregion

    //region InteractorOutput

    //TODO: Implement your InteractorOutput methods here

    //endregion
}