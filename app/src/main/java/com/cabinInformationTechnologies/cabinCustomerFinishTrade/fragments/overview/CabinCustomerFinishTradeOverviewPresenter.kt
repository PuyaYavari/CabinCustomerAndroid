package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.overview

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAgreements

class CabinCustomerFinishTradeOverviewPresenter(var view: CabinCustomerFinishTradeOverviewContracts.View?) :
    CabinCustomerFinishTradeOverviewContracts.Presenter,
    CabinCustomerFinishTradeOverviewContracts.InteractorOutput {

    var interactor: CabinCustomerFinishTradeOverviewContracts.Interactor? =
        CabinCustomerFinishTradeOverviewInteractor(
            this
        )
    var router: CabinCustomerFinishTradeOverviewContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router =
            CabinCustomerFinishTradeOverviewRouter(
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

    override fun listAgreements(context: Context?, orderId: Int?) {
        if (context != null && orderId != null)
            interactor?.listAgreements(context, orderId)
    }

    //endregion

    //region InteractorOutput

    override fun setAgreements(agreements: MODELAgreements) {
        view?.setAgreements(agreements)
    }

    override fun closeActivity() {
        view?.closeActivity()
    }

    //endregion
}