package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context

class MODELSize: LocalDataModel {
    var id: Int = -1
    lateinit var name: String

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONSize
            id = jsonData.id
            name = jsonData.name
            true
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                context,
                this::class.java.name,
                "A problem occurred while mapping Size.",
                exception)
            false
        }

    }
}