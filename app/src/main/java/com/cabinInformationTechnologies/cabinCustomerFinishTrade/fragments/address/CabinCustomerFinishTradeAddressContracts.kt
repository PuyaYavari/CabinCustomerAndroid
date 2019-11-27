package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.address

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddresses

object CabinCustomerFinishTradeAddressContracts {

    interface View : BaseContracts.View {
        fun setDeliveryAddresses(addresses: List<MODELAddress?>)
        fun setInvoiceAddresses(addresses: List<MODELAddress?>)
        fun showDeliveryAddressDetail(address: MODELAddress)
        fun showInvoiceAddressDetail(address: MODELAddress)
        fun setActivityDeliveryAddress(address: MODELAddress?)
        fun setActivityInvoiceAddress(address: MODELAddress?)
        fun getSelectedDeliveryAddress(): MODELAddress?
        fun getSelectedInvoiceAddress(): MODELAddress?
        fun setupNoDeliveryAddress()
        fun setupNoInvoiceAddress()
        fun showDeliveryAdd()
        fun hideDeliveryAdd()
        fun closeActivity()
    }

    interface Presenter : BaseContracts.Presenter {
        var deliveryAddress: MODELAddress?
        var invoiceAddress: MODELAddress?

        fun getAddresses(context: Context)
        fun setUseDelivery(useDelivery: Boolean)
    }

    interface Interactor : BaseContracts.Interactor {
        fun getAddresses(context: Context)
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        fun setAddresses(addresses: MODELAddresses)
        fun closeActivity()
    }

    interface Router : BaseContracts.Router
}