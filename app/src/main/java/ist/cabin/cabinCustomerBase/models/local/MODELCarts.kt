package ist.cabin.cabinCustomerBase.models.local

import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.APICart

class MODELCarts: LocalDataModel {
    private val carts: MutableList<MODELCart> = mutableListOf()

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as APICart
            jsonData.cart?.forEach {
                val cart = MODELCart()
                if (cart.mapFrom(it))
                    carts.add(cart)
            }
            true
        } catch (exception: Exception) {
            Logger.warn(this::class.java.name, "A problem occurred while mapping Carts.", exception)
            false
        }
    }
    
    fun getCarts(): List<MODELCart> = carts
}