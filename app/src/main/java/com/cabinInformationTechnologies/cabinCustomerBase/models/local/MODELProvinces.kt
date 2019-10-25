package com.cabinInformationTechnologies.cabinCustomerBase.models.local

class MODELProvinces: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    private val provinces = mutableListOf<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProvince>()

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APICity
            jsonData.cities.forEach {
                val city = com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProvince()
                if (city.mapFrom(it))
                    provinces.add(city)
            }
            true
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(this::class.java.name, "Problem while mapping Provinces!", exception)
            false
        }
    }

    fun getProvinces() = this.provinces
}