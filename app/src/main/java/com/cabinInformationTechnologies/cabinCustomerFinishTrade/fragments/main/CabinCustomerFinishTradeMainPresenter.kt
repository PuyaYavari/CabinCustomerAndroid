package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.main

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELCart
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSeller

class CabinCustomerFinishTradeMainPresenter(var view: CabinCustomerFinishTradeMainContracts.View?) :
    CabinCustomerFinishTradeMainContracts.Presenter,
    CabinCustomerFinishTradeMainContracts.InteractorOutput {

    var interactor: CabinCustomerFinishTradeMainContracts.Interactor? =
        CabinCustomerFinishTradeMainInteractor(
            this
        )
    var router: CabinCustomerFinishTradeMainContracts.Router? = null

    private var priceDetailIsVisible: Boolean = false

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router =
            CabinCustomerFinishTradeMainRouter(
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

    override fun getCart(context: Context?) {
        if (context != null)
            interactor?.getCart(context)
    }

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

    override fun moveToDeliveryAddressDetail(address: MODELAddress?) {
        router?.moveToDeliveryAddressDetail(address)
    }

    override fun moveToInvoiceAddressDetail(address: MODELAddress?) {
        router?.moveToInvoiceAddressDetail(address)
    }

    override fun sendAddresses(context: Context, delivery: MODELAddress?, invoice: MODELAddress?) {
        if (delivery != null) {
            if (invoice != null && delivery.id != invoice.id)
                interactor?.sendAddresses(context, delivery, invoice)
            else
                interactor?.sendAddresses(context, delivery, null)
        }
    }

    //endregion

    //region InteractorOutput

    override fun setCart(cart: MODELCart?) {
        if (cart != null) {
            view?.setupPriceDetails(cart)
            view?.setActivityPrice(cart.getTotal())

            val sellerIter: MutableIterator<MODELSeller> = cart.getSellers().iterator()
            view?.clearCargoPrices()
            while (sellerIter.hasNext()) {
                val seller = sellerIter.next()
                val shippingPrice = seller.getShippingPrice()
                if (shippingPrice != 0.0 && shippingPrice != null ) {
                    view?.addShippingPrice(seller.getName(), shippingPrice)
                }
            }

            view?.setupPage()
        }
    }

    override fun feedback(message: String?) {
        if (message == null)
            view?.showErrorMessage(
                view?.getActivityContext()?.resources?.getString(R.string.default_error_message)
                    .toString())
        else
            view?.showErrorMessage(message)
    }

    override fun toastFeedback(message: String?) {
        if (message == null)
            Toast.makeText(
                view?.getActivityContext(),
                view?.getActivityContext()?.resources?.getString(R.string.default_error_message),
                Toast.LENGTH_SHORT
            ).show()
        else
            Toast.makeText(
                view?.getActivityContext(),
                message,
                Toast.LENGTH_SHORT
            ).show()
    }

    override fun setActivityOrderId(id: Int) {
        view?.setActivityOrderId(id)
    }

    //endregion
}