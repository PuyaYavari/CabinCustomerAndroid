package com.cabinInformationTechnologies.cabinCustomerBase.models.local

class MODELDistricts: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    private val districts: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELDistrict> = mutableListOf()

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIDistrict
            jsonData.districts.forEach {
                val district = com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELDistrict()
                if (district.mapFrom(it))
                    districts.add(district)
            }
            true
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(this::class.java.name, "Problem while mapping to model.", exception)
            false
        }
    }

    fun getDistricts() = this.districts
}