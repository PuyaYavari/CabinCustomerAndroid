package ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions.adapter

abstract class AddressesListTypeID {

    abstract val type: Int

    companion object {
        val NO_ADDRESS_TYPE = 0
        val ADDRESS_TYPE = 1
        val TAX_INVOICE_ADDRESS_TYPE = 2
    }
}