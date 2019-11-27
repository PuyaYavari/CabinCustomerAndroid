package com.cabinInformationTechnologies.cabinCustomerFinishTrade

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress

object CabinCustomerFinishTradeContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        fun setDeliveryAddress(address: MODELAddress?)
        fun setInvoiceAddress(address: MODELAddress?)
        fun addressesSelected(): Boolean?
        fun paymentSelected(): Boolean?
        fun notifySuccess()
        fun finishActivity()
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        var orderId: Int?
        var deliveryAddress: MODELAddress?
        var invoiceAddress: MODELAddress?
        var price: Double?
        var PIFAccepted: Boolean
        var DSAAccepted: Boolean

        fun addressesSelected(): Boolean
        fun paymentSelected(): Boolean
        fun activateOrder(context: Context?)
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {
        fun activateOrder(context: Context, orderId: Int)
    }

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput {
        fun success()
    }

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router

    interface ChangeAddAddressCallback {
        fun Delivery(address: MODELAddress?)
        fun Invoice(address: MODELAddress?)
    }
}