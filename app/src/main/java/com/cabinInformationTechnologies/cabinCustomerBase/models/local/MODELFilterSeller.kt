package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context

class MODELFilterSeller: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    lateinit var name: String
    var id: Int? = null
    var amount: Int = 0
    var isSelected: Boolean = false

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONFilterSeller
            name = jsonData.name
            id = jsonData.id
            amount = jsonData.amount
            isSelected = jsonData.isSelected
            true
        } catch (exception: Exception){
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                context,
                this::class.java.name,
                "A problem occurred while mapping Seller.",
                exception)
            false
        }
    }
}