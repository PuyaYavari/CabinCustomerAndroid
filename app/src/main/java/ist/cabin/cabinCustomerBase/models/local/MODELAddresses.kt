package ist.cabin.cabinCustomerBase.models.local

import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.APIAddress

class MODELAddresses : LocalDataModel {
    private val addresses: MutableList<MODELAddress?> = mutableListOf()

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as APIAddress
            jsonData.addresses.forEach {
                val address = MODELAddress()
                if (address.mapFrom(it))
                    addresses.add(address)
            }
            true
        } catch (exception: Exception) {
            Logger.error(this::class.java.name,
                "A problem occurred while mapping Addresses!",
                exception
            )
            false
        }
    }

    fun getAddresses() = this.addresses
}