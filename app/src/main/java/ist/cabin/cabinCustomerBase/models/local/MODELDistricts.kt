package ist.cabin.cabinCustomerBase.models.local

import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.APIDistrict

class MODELDistricts: LocalDataModel {
    private val districts: MutableList<MODELDistrict> = mutableListOf()

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as APIDistrict
            jsonData.districts.forEach {
                val district = MODELDistrict()
                if (district.mapFrom(it))
                    districts.add(district)
            }
            true
        } catch (exception: Exception) {
            Logger.error(this::class.java.name, "Problem while mapping to model.", exception)
            false
        }
    }

    fun getDistricts() = this.districts
}