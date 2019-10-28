package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context

class MODELFilterColor: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    private var id: Int = -1
    private var name: String = ""
    var hexCode: String = ""
    var rgb: String? = null
    private var amount: Int = 0
    private var isSelected: Boolean = false

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONFilterColor
            this.id = jsonData.id
            val nameData = jsonData.name
            if (nameData != null)
                this.name = nameData
            this.hexCode = jsonData.hexCode
            this.rgb = jsonData.rgbCode
            this.amount = jsonData.amount
            this.isSelected = jsonData.isSelected
            true
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                context,
                this::class.java.name,
                "Failed to map rawColor!",
                exception)
            false
        }
    }

    fun getId(): Int = id
    fun getName(): String = name
    fun getAmount(): Int = amount
    fun getIsSelected(): Boolean = isSelected
}