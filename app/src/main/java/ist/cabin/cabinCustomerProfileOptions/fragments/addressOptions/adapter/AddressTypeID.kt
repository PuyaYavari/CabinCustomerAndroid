package ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions.adapter

abstract class AddressTypeID {
    abstract val type: Int

    companion object {
        val DELIVERY = 0
        val INVOICE = 1
    }
}