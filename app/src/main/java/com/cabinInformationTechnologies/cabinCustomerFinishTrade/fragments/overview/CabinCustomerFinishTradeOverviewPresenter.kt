package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.overview

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAgreements

class CabinCustomerFinishTradeOverviewPresenter(var view: com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.overview.CabinCustomerFinishTradeOverviewContracts.View?) :
    com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.overview.CabinCustomerFinishTradeOverviewContracts.Presenter,
    com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.overview.CabinCustomerFinishTradeOverviewContracts.InteractorOutput {

    var interactor: com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.overview.CabinCustomerFinishTradeOverviewContracts.Interactor? =
        com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.overview.CabinCustomerFinishTradeOverviewInteractor(
            this
        )
    var router: com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.overview.CabinCustomerFinishTradeOverviewContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router =
            com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.overview.CabinCustomerFinishTradeOverviewRouter(
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

    override fun listAgreements(context: Context?, orderId: Int?) {
        if (context != null && orderId != null)
            interactor?.listAgreements(context, orderId)
    }

    //endregion

    //region InteractorOutput

    override fun setAgreements(agreements: MODELAgreements) {
        view?.setAgreements(agreements)
    }

    //endregion
}