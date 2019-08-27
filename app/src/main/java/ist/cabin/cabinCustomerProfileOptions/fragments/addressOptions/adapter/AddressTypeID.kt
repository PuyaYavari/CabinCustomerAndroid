package ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions.adapter

abstract class AddressTypeID {
    abstract val type: Int

    companion object {
        const val DELIVERY: Int = 0
        const val INVOICE: Int = 1
    }
}