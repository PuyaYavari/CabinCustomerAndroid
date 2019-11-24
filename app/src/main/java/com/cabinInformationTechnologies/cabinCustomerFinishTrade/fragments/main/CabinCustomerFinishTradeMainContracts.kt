package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.main

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELCart

object CabinCustomerFinishTradeMainContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        fun setupPage()
        fun setupPriceDetails(cart: MODELCart)
        fun addShippingPrice(sellerName: String, price: Double)
        fun clearCargoPrices()
        fun showPriceDetail()
        fun hidePriceDetail()
        fun pageForward()
        fun setPage(page: Int)
        fun getPage(): Int
        fun moveOut()
        fun setupFirstPage()
        fun setupSecondPage()
        fun setupLastPage()
        fun moveIndicatorTo(fromId: Int, toId: Int)
        fun showErrorMessage(message: String)
        fun setActivityPrice(price: Double)
        fun setActivityOrderId(id: Int)
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        fun getCart(context: Context?)
        fun togglePriceDetail()
        fun pageForward(currentPosition: Int)
        fun pageBackward(currentPosition: Int)
        fun pageBackToFirstPage()
        fun moveToDeliveryAddressDetail(address: MODELAddress?)
        fun moveToInvoiceAddressDetail(address: MODELAddress?)
        fun sendAddresses(context: Context, delivery: MODELAddress?, invoice: MODELAddress?)
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {
        fun getCart(context: Context)
        fun sendAddresses(context: Context, delivery: MODELAddress, invoice: MODELAddress?)
    }

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput {
        fun setCart(cart: MODELCart?)
        fun feedback(message: String?)
        fun toastFeedback(message: String?)
        fun pageForward(currentPosition: Int)
        fun setActivityOrderId(id: Int)
    }

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router {
        fun moveToDeliveryAddressDetail(address: MODELAddress?)
        fun moveToInvoiceAddressDetail(address: MODELAddress?)
    }

}