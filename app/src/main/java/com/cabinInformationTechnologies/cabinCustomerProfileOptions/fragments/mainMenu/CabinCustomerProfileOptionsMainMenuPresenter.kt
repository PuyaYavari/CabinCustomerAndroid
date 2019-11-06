package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.mainMenu

import android.app.Activity
import android.os.Bundle

class CabinCustomerProfileOptionsMainMenuPresenter(var view: com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.mainMenu.CabinCustomerProfileOptionsMainMenuContracts.View?) :
    com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.mainMenu.CabinCustomerProfileOptionsMainMenuContracts.Presenter,
    com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.mainMenu.CabinCustomerProfileOptionsMainMenuContracts.InteractorOutput {

    var interactor: com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.mainMenu.CabinCustomerProfileOptionsMainMenuContracts.Interactor? =
        com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.mainMenu.CabinCustomerProfileOptionsMainMenuInteractor(
            this
        )
    var router: com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.mainMenu.CabinCustomerProfileOptionsMainMenuContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router =
            com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.mainMenu.CabinCustomerProfileOptionsMainMenuRouter(
                activity
            )

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

    override fun moveToPersonalDataPage() { router?.moveToPersonalDataPage() }
    override fun moveToAddressOptionsPage() { router?.moveToAddressOptionsPage() }
    override fun moveToChangePasswordPage() { router?.moveToChangePasswordPage() }
    override fun moveToNotificationChoicesPage() { router?.moveToNotificationChoicesPage() }

    //endregion

    //region InteractorOutput

    //endregion
}