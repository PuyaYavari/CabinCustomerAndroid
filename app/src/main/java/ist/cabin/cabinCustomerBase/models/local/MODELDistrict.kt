package ist.cabin.cabinCustomerBase.models.local

import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.JSONDistrict

class MODELDistrict : LocalDataModel{
    var id: Int = -1
    var name: String = ""
    var code: String = ""
    var cityCode: String? = null

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONDistrict
            id = jsonData.id
            name = jsonData.name
            val codeData = jsonData.code
            if (codeData != null)
                code = codeData
            cityCode = jsonData.cityCode
            true
        } catch (exception: Exception) {
            Logger.error(this::class.java.name, "Problem while mapping to model.", exception)
            false
        }
    }

    override fun toString(): String = this.name
}