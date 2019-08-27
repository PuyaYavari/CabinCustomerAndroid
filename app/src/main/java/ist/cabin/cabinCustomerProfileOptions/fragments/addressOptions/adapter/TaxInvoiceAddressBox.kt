package ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions.adapter

import ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions.CabinCustomerAddressOptionsContracts

class TaxInvoiceAddressBox: CabinCustomerAddressOptionsContracts.Addressbox {

    /* setter getter for parameters *///TODO

    override fun getType(): Int {
        return AddressesListTypeID.TAX_INVOICE_ADDRESS_TYPE
    }

    override fun getAddressTypeID(): Int {
        return AddressTypeID.INVOICE
    }
}