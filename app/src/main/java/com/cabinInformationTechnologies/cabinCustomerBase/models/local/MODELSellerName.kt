package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context

class MODELSellerName: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    lateinit var name: String
    var id: Int? = null

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonModel = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONSellerName
            name = jsonModel.name
            id = jsonModel.id
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