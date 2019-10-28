package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context

class MODELImage: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    lateinit var url: String
    var priority: Boolean = false

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            if (modelData == null)
                com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                    context,
                    this::class.java.name,
                    "Image is null.",
                    null)
            val jsonModel = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONImage
            url = jsonModel.url
            if (jsonModel.priority != null)
                priority = jsonModel.priority
            true
        } catch (exception : Exception){
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                context,
                this::class.java.name,
                "A problem occurred while mapping Image.",
                exception)
            false
        }
    }
}