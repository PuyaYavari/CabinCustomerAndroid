package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context

class MODELFilterPrice: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    private var id: Int = -1
    private var name: String = ""
    var amount: Int = 0
    var isSelected: Boolean = false

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONFilterPrice
            this.id = jsonData.id
            this.name = jsonData.name
            this.amount = jsonData.amount
            this.isSelected = jsonData.isSelected
            true
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                context,
                this::class.java.name,
                "Failed to map PriceInterval!",
                exception)
            false
        }
    }

    fun getId(): Int = id
    fun getName(): String = name
}