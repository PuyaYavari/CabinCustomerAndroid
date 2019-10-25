package com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionDetailDenied

import android.app.Activity
import android.os.Bundle

class CabinCustomerExtraditionDetailDeniedPresenter(var view: com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionDetailDenied.CabinCustomerExtraditionDetailDeniedContracts.View?) :
    com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionDetailDenied.CabinCustomerExtraditionDetailDeniedContracts.Presenter,
    com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionDetailDenied.CabinCustomerExtraditionDetailDeniedContracts.InteractorOutput {

    var interactor: com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionDetailDenied.CabinCustomerExtraditionDetailDeniedContracts.Interactor? =
        com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionDetailDenied.CabinCustomerExtraditionDetailDeniedInteractor(
            this
        )
    var router: com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionDetailDenied.CabinCustomerExtraditionDetailDeniedContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router =
            com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionDetailDenied.CabinCustomerExtraditionDetailDeniedRouter(
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