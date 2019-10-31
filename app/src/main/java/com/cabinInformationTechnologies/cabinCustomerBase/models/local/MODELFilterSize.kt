package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context

class MODELFilterSize: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    private var id: Int = -1
    private var name: String = ""
    var amount: Int = 0
    var isSelected: Boolean = false

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONFilterSize
            id = jsonData.id
            name = jsonData.name
            amount = jsonData.amount
            isSelected = jsonData.isSelected
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

    fun getId() = id
    fun getName() = name
}