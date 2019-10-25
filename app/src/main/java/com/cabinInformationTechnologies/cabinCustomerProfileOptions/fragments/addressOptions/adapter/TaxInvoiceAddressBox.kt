package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.adapter

class TaxInvoiceAddressBox(private val address: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress): com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.CabinCustomerAddressOptionsContracts.Addressbox {


    /* setter getter for parameters *///TODO

    override fun getType(): Int {
        return com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.adapter.AddressesListTypeID.Companion.TAX_INVOICE_ADDRESS_TYPE
    }

    override fun getAddress(): com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress {
        return address
    }

    override fun isInvoice(): Boolean {
        return true
    }
}