package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONCargo

class MODELCargo: LocalDataModel {
    private var id: Int = -1
    private var name: String = ""
    private var logoUrl: String? = null
    private var trackingCode: String = ""

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONCargo
            this.id = jsonData.id
            this.name = jsonData.name
            this.logoUrl = jsonData.logoUrl
            this.trackingCode = jsonData.trackingCode
            true
        } catch (exception: Exception) {
            Logger.warn(
                context,
                this::class.java.name,
                "Error while mapping cargo!",
                exception
            )
            false
        }
    }

    fun getId(): Int = id
    fun getName(): String = name
    fun getLogoUrl(): String? = logoUrl
    fun getTrackingCode(): String = trackingCode
}