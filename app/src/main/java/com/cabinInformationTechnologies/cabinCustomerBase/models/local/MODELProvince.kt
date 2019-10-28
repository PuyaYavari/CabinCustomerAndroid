package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context

class MODELProvince: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    var id: Int = -1
    var name: String = ""
    var code: String = ""

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONCity
            id = jsonData.id
            name = jsonData.name
            val codeData = jsonData.code
            if (codeData != null)
                code = codeData
            true
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                context,
                this::class.java.name,
                "Problem while mapping Province!",
                exception)
            false
        }
    }

    override fun toString(): String = name.toString()
}