package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.payment

import android.app.Activity
import android.os.Bundle

class CabinCustomerFinishTradePaymentPresenter(var view: CabinCustomerFinishTradePaymentContracts.View?) :
    CabinCustomerFinishTradePaymentContracts.Presenter,
    CabinCustomerFinishTradePaymentContracts.InteractorOutput {

    var interactor: CabinCustomerFinishTradePaymentContracts.Interactor? =
        CabinCustomerFinishTradePaymentInteractor(
            this
        )
    var router: CabinCustomerFinishTradePaymentContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router =
            CabinCustomerFinishTradePaymentRouter(
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

    //endregion

    //region InteractorOutput

    //endregion
}