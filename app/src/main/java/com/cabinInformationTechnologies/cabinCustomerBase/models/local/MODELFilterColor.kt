package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONFilterColor

class MODELFilterColor: LocalDataModel {
    private var id: Int = -1
    private var name: String = ""
    var hexCode: String = ""
    var rgb: String? = null
    var amount: Int = 0
    var isSelected: Boolean = false

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONFilterColor
            if (jsonData.amount > 0) {
                this.id = jsonData.id
                val nameData = jsonData.name
                if (nameData != null)
                    this.name = nameData
                this.hexCode = jsonData.hexCode
                this.rgb = jsonData.rgbCode
                this.amount = jsonData.amount
                this.isSelected = jsonData.isSelected
                true
            } else {
                false
            }
        } catch (exception: Exception) {
            Logger.error(
                context,
                this::class.java.name,
                "Failed to map rawColor!",
                exception)
            false
        }
    }

    fun getId(): Int = id
    fun getName(): String = name
}