package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context

class MODELPersonalInfos: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    private var personalInfos: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELPersonalInfo> = mutableListOf()

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIPersonalInfo
            jsonData.personalInfo.forEach {
                val personalInfo = com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELPersonalInfo()
                if (personalInfo.mapFrom(context, it))
                    personalInfos.add(personalInfo)
            }
            true
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                context,
                this::class.java.name,
                "A problem occurred while mapping PersonalInfos.",
                exception)
            false
        }
    }

    fun getPersonalInfos() = personalInfos
}