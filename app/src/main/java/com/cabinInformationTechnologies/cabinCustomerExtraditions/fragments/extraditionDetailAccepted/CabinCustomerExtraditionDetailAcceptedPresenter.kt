package com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionDetailAccepted

import android.app.Activity
import android.os.Bundle

class CabinCustomerExtraditionDetailAcceptedPresenter(var view: com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionDetailAccepted.CabinCustomerExtraditionDetailAcceptedContracts.View?) :
    com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionDetailAccepted.CabinCustomerExtraditionDetailAcceptedContracts.Presenter,
    com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionDetailAccepted.CabinCustomerExtraditionDetailAcceptedContracts.InteractorOutput {

    var interactor: com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionDetailAccepted.CabinCustomerExtraditionDetailAcceptedContracts.Interactor? =
        com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionDetailAccepted.CabinCustomerExtraditionDetailAcceptedInteractor(
            this
        )
    var router: com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionDetailAccepted.CabinCustomerExtraditionDetailAcceptedContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router =
            com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionDetailAccepted.CabinCustomerExtraditionDetailAcceptedRouter(
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

    //TODO: Implement your Presenter methods here

    //endregion

    //region InteractorOutput

    //TODO: Implement your InteractorOutput methods here

    //endregion
}