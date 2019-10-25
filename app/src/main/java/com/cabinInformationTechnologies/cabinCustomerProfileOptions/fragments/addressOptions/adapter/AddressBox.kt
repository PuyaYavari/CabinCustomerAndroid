package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.adapter

class AddressBox(private val address: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress): com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.CabinCustomerAddressOptionsContracts.Addressbox {

    override fun getType(): Int {
        return com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.adapter.AddressesListTypeID.Companion.ADDRESS_TYPE
    }

    override fun getAddress(): com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress {
        return address
    }

    override fun isInvoice(): Boolean {
        return address.isInvoice
    }
}