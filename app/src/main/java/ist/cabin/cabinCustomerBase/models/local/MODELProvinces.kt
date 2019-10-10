package ist.cabin.cabinCustomerBase.models.local

import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.APICity

class MODELProvinces: LocalDataModel {
    private val provinces = mutableListOf<MODELProvince>()

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as APICity
            jsonData.cities.forEach {
                val city = MODELProvince()
                if (city.mapFrom(it))
                    provinces.add(city)
            }
            true
        } catch (exception: Exception) {
            Logger.error(this::class.java.name, "Problem while mapping Provinces!", exception)
            false
        }
    }

    fun getProvinces() = this.provinces
}