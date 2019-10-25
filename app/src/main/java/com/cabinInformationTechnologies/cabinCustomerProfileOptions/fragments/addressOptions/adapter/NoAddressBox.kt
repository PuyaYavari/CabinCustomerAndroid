package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.adapter

class NoAddressBox(private val isInvoice: Boolean): com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.CabinCustomerAddressOptionsContracts.Addressbox {

    override fun getType(): Int {
        return com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.adapter.AddressesListTypeID.Companion.NO_ADDRESS_TYPE
    }

    override fun getAddress(): com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress? {
        return null
    }

    override fun isInvoice(): Boolean {
        return isInvoice
    }
}