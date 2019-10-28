package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context

class MODELDistricts: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    private val districts: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELDistrict> = mutableListOf()

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIDistrict
            jsonData.districts.forEach {
                val district = com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELDistrict()
                if (district.mapFrom(context, it))
                    districts.add(district)
            }
            true
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                context,
                this::class.java.name,
                "Problem while mapping to model.",
                exception)
            false
        }
    }

    fun getDistricts() = this.districts
}