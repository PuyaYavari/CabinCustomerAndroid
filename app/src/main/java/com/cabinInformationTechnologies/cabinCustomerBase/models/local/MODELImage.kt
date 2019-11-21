package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context

class MODELImage: LocalDataModel {
    private var url: String = ""
    private var extension: String? = ""
    private var priority: Boolean = false

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            if (modelData == null)
                com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                    context,
                    this::class.java.name,
                    "Image is null.",
                    null)
            val jsonModel = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONImage
            this.url = jsonModel.url
            this.extension = jsonModel.extension
            if (jsonModel.priority != null)
                this.priority = jsonModel.priority
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

    fun getURL() = this.url
    fun getExtension() = this.extension
    fun getPriority() = this.priority
}