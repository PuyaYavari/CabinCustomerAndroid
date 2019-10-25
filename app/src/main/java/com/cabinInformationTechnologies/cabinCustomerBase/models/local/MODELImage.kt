package com.cabinInformationTechnologies.cabinCustomerBase.models.local

class MODELImage: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    lateinit var url: String
    var priority: Boolean = false

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            if (modelData == null)
                com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(this::class.java.name, "Image is null.", null)
            val jsonModel = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONImage
            url = jsonModel.url
            if (jsonModel.priority != null)
                priority = jsonModel.priority
            true
        } catch (exception : Exception){
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "A problem occurred while mapping Image.", exception)
            false
        }
    }
}