package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.address

import android.app.Activity
import android.os.Bundle

class CabinCustomerFinishTradeAddressPresenter(var view: com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.address.CabinCustomerFinishTradeAddressContracts.View?) :
    com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.address.CabinCustomerFinishTradeAddressContracts.Presenter,
    com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.address.CabinCustomerFinishTradeAddressContracts.InteractorOutput {

    var interactor: com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.address.CabinCustomerFinishTradeAddressContracts.Interactor? =
        com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.address.CabinCustomerFinishTradeAddressInteractor(
            this
        )
    var router: com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.address.CabinCustomerFinishTradeAddressContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router =
            com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.address.CabinCustomerFinishTradeAddressRouter(
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