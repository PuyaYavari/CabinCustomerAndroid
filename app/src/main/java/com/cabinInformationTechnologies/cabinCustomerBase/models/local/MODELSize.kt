package com.cabinInformationTechnologies.cabinCustomerBase.models.local

class MODELSize: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    var id: Int = -1
    lateinit var name: String

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONSize
            id = jsonData.id
            name = jsonData.name
            true
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "A problem occurred while mapping Size.", exception)
            false
        }

    }
}