package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.mainMenu

import android.app.Activity
import android.os.Bundle

class CabinCustomerProfileOptionsMainMenuPresenter(var view: CabinCustomerProfileOptionsMainMenuContracts.View?) :
    CabinCustomerProfileOptionsMainMenuContracts.Presenter,
    CabinCustomerProfileOptionsMainMenuContracts.InteractorOutput {

    var interactor: CabinCustomerProfileOptionsMainMenuContracts.Interactor? =
        CabinCustomerProfileOptionsMainMenuInteractor(
            this
        )
    var router: CabinCustomerProfileOptionsMainMenuContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router =
            CabinCustomerProfileOptionsMainMenuRouter(
                activity
            )
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