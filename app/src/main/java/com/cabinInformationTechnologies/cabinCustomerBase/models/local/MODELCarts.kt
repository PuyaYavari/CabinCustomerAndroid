package com.cabinInformationTechnologies.cabinCustomerBase.models.local

class MODELCarts: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    private val carts: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELCart> = mutableListOf()

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APICart
            jsonData.cart?.forEach {
                val cart = com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELCart()
                if (cart.mapFrom(it))
                    carts.add(cart)
            }
            true
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "A problem occurred while mapping Carts.", exception)
            false
        }
    }
    
    fun getCarts(): List<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELCart> = carts
}