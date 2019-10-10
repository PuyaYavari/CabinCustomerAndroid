package ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions.adapter

import ist.cabin.cabinCustomerBase.models.local.MODELAddress
import ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions.CabinCustomerAddressOptionsContracts

class TaxInvoiceAddressBox(private val address: MODELAddress): CabinCustomerAddressOptionsContracts.Addressbox {


    /* setter getter for parameters *///TODO

    override fun getType(): Int {
        return AddressesListTypeID.TAX_INVOICE_ADDRESS_TYPE
    }

    override fun getAddress(): MODELAddress {
        return address
    }

    override fun isInvoice(): Boolean {
        return true
    }
}