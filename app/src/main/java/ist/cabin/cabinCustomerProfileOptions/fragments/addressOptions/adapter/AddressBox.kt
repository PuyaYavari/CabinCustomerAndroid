package ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions.adapter

import ist.cabin.cabinCustomerBase.models.local.MODELAddress
import ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions.CabinCustomerAddressOptionsContracts

class AddressBox(private val address: MODELAddress): CabinCustomerAddressOptionsContracts.Addressbox {

    override fun getType(): Int {
        return AddressesListTypeID.ADDRESS_TYPE
    }

    override fun getAddress(): MODELAddress {
        return address
    }

    override fun isInvoice(): Boolean {
        return address.isInvoice
    }
}