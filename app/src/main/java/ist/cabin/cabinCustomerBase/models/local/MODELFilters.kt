package ist.cabin.cabinCustomerBase.models.local

import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.APIFilter

class MODELFilters: LocalDataModel {
    private val filters: MutableList<MODELFilter?> = mutableListOf()

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as APIFilter
            if (!jsonData.filters.isNullOrEmpty()) {
                jsonData.filters.forEach {
                    val filter = MODELFilter()
                    if (filter.mapFrom(it))
                        this.filters.add(filter)
                }
            }
            true
        } catch (exception: Exception) {
            Logger.error(this::class.java.name, "Failed to map Filters!", exception)
            false
        }
    }

    fun getFilters(): MutableList<MODELFilter?> = filters
}