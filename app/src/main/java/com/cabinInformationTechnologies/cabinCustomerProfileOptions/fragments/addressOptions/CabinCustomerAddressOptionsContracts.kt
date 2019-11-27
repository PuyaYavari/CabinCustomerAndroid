package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions

import android.content.Context
import androidx.navigation.NavController
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddresses

object CabinCustomerAddressOptionsContracts {

    interface View : BaseContracts.View {
        fun setupEmptyDeliveryAddressList()
        fun setupEmptyInvoiceAddressList()
        fun setupDeliveryAddressList(myDataset: MutableList<Addressbox>)
        fun setupInvoiceAddressList(myDataset: MutableList<Addressbox>)
        fun addDeliveryAddressListener(address: MODELAddress?)
        fun addInvoiceAddressListener(address: MODELAddress?)
        fun removeAddress(address: MODELAddress?)
        fun undoAddressRemove()
    }

    interface Presenter : BaseContracts.Presenter {
        fun setupPage()
        fun getAddresses(context: Context, navController: NavController)
        fun setupDeliveryAddressList()
        fun setupInvoiceAddressList()
        fun moveToAddDeliveryAddressPage(address: MODELAddress?)
        fun moveToAddInvoiceAddressPage(address: MODELAddress?)
        fun removeAddress(context: Context, address: MODELAddress)
    }

    interface Interactor : BaseContracts.Interactor {
        fun getAddresses(context: Context, navController: NavController)
        fun removeAddress(context: Context, address: MODELAddress)
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        fun setAddresses(addresses: MODELAddresses)
        fun undoRemove()
    }

    interface Router : BaseContracts.Router {
        fun moveToAddDeliveryAddressPage(address: MODELAddress?)
        fun moveToAddInvoiceAddressPage(address: MODELAddress?)
    }

    interface Addressbox {
        fun getType(): Int
        fun getAddress(): MODELAddress?
        fun isInvoice(): Boolean
    }

}