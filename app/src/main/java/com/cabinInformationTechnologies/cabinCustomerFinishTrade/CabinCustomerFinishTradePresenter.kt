package com.cabinInformationTechnologies.cabinCustomerFinishTrade

import android.app.Activity
import android.os.Bundle

class CabinCustomerFinishTradePresenter(var view: com.cabinInformationTechnologies.cabinCustomerFinishTrade.CabinCustomerFinishTradeContracts.View?) :
    com.cabinInformationTechnologies.cabinCustomerFinishTrade.CabinCustomerFinishTradeContracts.Presenter,
    com.cabinInformationTechnologies.cabinCustomerFinishTrade.CabinCustomerFinishTradeContracts.InteractorOutput {

    var interactor: com.cabinInformationTechnologies.cabinCustomerFinishTrade.CabinCustomerFinishTradeContracts.Interactor? =
        com.cabinInformationTechnologies.cabinCustomerFinishTrade.CabinCustomerFinishTradeInteractor(this)
    var router: com.cabinInformationTechnologies.cabinCustomerFinishTrade.CabinCustomerFinishTradeContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = com.cabinInformationTechnologies.cabinCustomerFinishTrade.CabinCustomerFinishTradeRouter(activity)

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


    //endregion

    //region InteractorOutput

    //TODO: Implement your InteractorOutput methods here

    //endregion
}