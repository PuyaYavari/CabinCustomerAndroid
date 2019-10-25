package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions

import android.content.Context

object CabinCustomerAddressOptionsContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        fun setupEmptyDeliveryAddressList()
        fun setupEmptyInvoiceAddressList()
        fun setupDeliveryAddressList(myDataset: MutableList<com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.CabinCustomerAddressOptionsContracts.Addressbox>)
        fun setupInvoiceAddressList(myDataset: MutableList<com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.CabinCustomerAddressOptionsContracts.Addressbox>)
        fun addDeliveryAddressListener(address: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress?)
        fun addInvoiceAddressListener(address: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress?)
        fun removeAddress(address: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress?)
        fun undoAddressRemove()
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        fun setupPage()
        fun getAddresses(context: Context)
        fun setupDeliveryAddressList()
        fun setupInvoiceAddressList()
        fun moveToAddDeliveryAddressPage(address: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress?)
        fun moveToAddInvoiceAddressPage(address: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress?)
        fun removeAddress(context: Context, address: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress)
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {
        fun getAddresses(context: Context)
        fun removeAddress(context: Context, address: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress)
    }

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput {
        fun setAddresses(addresses: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddresses)
        fun addressRemovedFeedback(isRemoved: Boolean, message: String?)
    }

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router {
        fun moveToAddDeliveryAddressPage(address: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress?)
        fun moveToAddInvoiceAddressPage(address: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress?)
    }

    interface Addressbox {
        fun getType(): Int
        fun getAddress(): com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress?
        fun isInvoice(): Boolean
    }

}