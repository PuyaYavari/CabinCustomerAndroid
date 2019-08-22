package ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions.adapter

import ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions.CabinCustomerAddressOptionsContracts

class AddressBox(private val addressType: Int): CabinCustomerAddressOptionsContracts.Addressbox {

    /* setter getter for parameters */

    override fun getType(): Int {
        return AddressesListTypeID.ADDRESS_TYPE
    }

    override fun getAddressTypeID(): Int {
        return addressType
    }
}