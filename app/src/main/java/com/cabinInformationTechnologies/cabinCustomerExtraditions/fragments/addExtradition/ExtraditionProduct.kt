package com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition

class ExtraditionProduct(val id: String, private val image: Int)
    :
    com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.CabinCustomerAddExtraditionContracts.Product {
    override var ER: String = ""
    override var temp: Int = 0

    override fun getID(): String {
        return id
    }

    override fun getProductImage(): Int {
        return image
    }
}