package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.main

import android.app.Activity
import android.os.Bundle
import com.cabinInformationTechnologies.cabin.R

class CabinCustomerFinishTradeMainPresenter(var view: com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.main.CabinCustomerFinishTradeMainContracts.View?) :
    com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.main.CabinCustomerFinishTradeMainContracts.Presenter,
    com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.main.CabinCustomerFinishTradeMainContracts.InteractorOutput {

    var interactor: com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.main.CabinCustomerFinishTradeMainContracts.Interactor? =
        com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.main.CabinCustomerFinishTradeMainInteractor(
            this
        )
    var router: com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.main.CabinCustomerFinishTradeMainContracts.Router? = null

    private var priceDetailIsVisible: Boolean = false

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router =
            com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.main.CabinCustomerFinishTradeMainRouter(
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

    override fun togglePriceDetail() {
        priceDetailIsVisible = if (!priceDetailIsVisible) {
            view!!.showPriceDetail()
            true
        } else {
            view!!.hidePriceDetail()
            false
        }
    }

    override fun pageForward(currentPosition: Int) {
        when (currentPosition) {
            0 -> {
                view!!.moveIndicatorTo(R.id.finishTradeStateIndicatorFirstPosition, R.id.finishTradeStateIndicatorSecondPosition)
                view!!.pageForward()
                view!!.setupSecondPage()
            }
            1 -> {
                view!!.moveIndicatorTo(R.id.finishTradeStateIndicatorSecondPosition, R.id.finishTradeStateIndicatorThirdPosition)
                view!!.pageForward()
                view!!.setupLastPage()
            }
            else -> {
                //TODO: move to finish page
            }
        }
    }

    override fun pageBackward(currentPosition: Int) {
        when (currentPosition) {
            2 -> {
                view!!.moveIndicatorTo(R.id.finishTradeStateIndicatorThirdPosition, R.id.finishTradeStateIndicatorSecondPosition)
                view!!.setPage(view!!.getPage() - 1)
                view!!.setupSecondPage()
            }
            1 -> {
                view!!.moveIndicatorTo(R.id.finishTradeStateIndicatorSecondPosition, R.id.finishTradeStateIndicatorFirstPosition)
                view!!.setPage(view!!.getPage() - 1)
                view!!.setupFirstPage()
            }
            else -> view!!.moveOut()
        }
    }

    override fun pageBackToFirstPage() {
        view!!.moveIndicatorTo(R.id.finishTradeStateIndicatorThirdPosition, R.id.finishTradeStateIndicatorFirstPosition)
        view!!.setPage(0)
        view!!.setupFirstPage()
    }

    //endregion

    //region InteractorOutput

    //TODO: Implement your InteractorOutput methods here

    //endregion
}