package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.adapter

abstract class AddressesListTypeID {

    abstract val type: Int

    companion object {
        const val NO_ADDRESS_TYPE: Int = 0
        const val ADDRESS_TYPE: Int = 1
        const val TAX_INVOICE_ADDRESS_TYPE: Int = 2
    }
}