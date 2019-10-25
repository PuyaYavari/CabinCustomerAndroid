package com.cabinInformationTechnologies.cabinCustomerBase.models.local

class MODELFilters: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    private val filters: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilter?> = mutableListOf()

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIFilter
            if (!jsonData.filters.isNullOrEmpty()) {
                jsonData.filters.forEach {
                    val filter = com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilter()
                    if (filter.mapFrom(it))
                        this.filters.add(filter)
                }
            }
            true
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(this::class.java.name, "Failed to map Filters!", exception)
            false
        }
    }

    fun getFilters(): MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilter?> = filters
}