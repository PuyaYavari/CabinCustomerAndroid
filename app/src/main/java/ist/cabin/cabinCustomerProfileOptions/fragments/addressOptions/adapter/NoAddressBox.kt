package ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions.adapter

import ist.cabin.cabinCustomerBase.models.local.MODELAddress
import ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions.CabinCustomerAddressOptionsContracts

class NoAddressBox(private val isInvoice: Boolean): CabinCustomerAddressOptionsContracts.Addressbox {

    override fun getType(): Int {
        return AddressesListTypeID.NO_ADDRESS_TYPE
    }

    override fun getAddress(): MODELAddress? {
        return null
    }

    override fun isInvoice(): Boolean {
        return isInvoice
    }
}