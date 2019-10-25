package com.cabinInformationTechnologies.cabinCustomerBase.models.local

class MODELProducts: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    var products: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct> = mutableListOf()

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIProduct
            jsonData.products.forEach {
                val product = com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct()
                if (product.mapFrom(it))
                    products.add(product)
            }
            true
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "A problem occurred while mapping Products.", exception)
            false
        }
    }
}