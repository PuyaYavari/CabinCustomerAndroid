package com.cabinInformationTechnologies.cabinCustomerBase.models.local

class MODELProvince: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    var id: Int = -1
    var name: String = ""
    var code: String = ""

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONCity
            id = jsonData.id
            name = jsonData.name
            val codeData = jsonData.code
            if (codeData != null)
                code = codeData
            true
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(this::class.java.name, "Problem while mapping Province!", exception)
            false
        }
    }

    override fun toString(): String = name.toString()
}