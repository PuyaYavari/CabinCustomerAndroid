package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context

class MODELProducts: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    var products: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct> = mutableListOf()

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIProduct
            jsonData.products.forEach {
                val product = com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct()
                if (product.mapFrom(context, it))
                    products.add(product)
            }
            true
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                context,
                this::class.java.name,
                "A problem occurred while mapping Products.",
                exception)
            false
        }
    }
}