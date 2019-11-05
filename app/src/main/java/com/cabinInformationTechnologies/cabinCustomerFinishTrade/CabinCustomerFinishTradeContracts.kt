package com.cabinInformationTechnologies.cabinCustomerFinishTrade

import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress

object CabinCustomerFinishTradeContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        fun setDeliveryAddress(address: MODELAddress?)
        fun setInvoiceAddress(address: MODELAddress?)
        fun addressesSelected(): Boolean?
        fun paymentSelected(): Boolean?
        fun contractAccepted(): Boolean?
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        var deliveryAddress: MODELAddress?
        var invoiceAddress: MODELAddress?

        fun addressesSelected(): Boolean
        fun paymentSelected(): Boolean
        fun contractAccepted(): Boolean
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {
        //TODO
    }

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router {
        //TODO
    }

    interface ChangeAddAddressCallback {
        fun Deliery(address: MODELAddress?)
        fun Invoice(address: MODELAddress?)
    }
}