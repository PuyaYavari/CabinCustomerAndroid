package ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions.adapter

import ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions.CabinCustomerAddressOptionsContracts

class NoAddressBox(private val addressType: Int): CabinCustomerAddressOptionsContracts.Addressbox {

    /* setter getter for parameters *///TODO

    override fun getType(): Int {
        return AddressesListTypeID.NO_ADDRESS_TYPE
    }

    override fun getAddressTypeID(): Int {
        return addressType
    }
}