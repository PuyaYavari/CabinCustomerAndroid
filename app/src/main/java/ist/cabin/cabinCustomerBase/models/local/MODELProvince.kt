package ist.cabin.cabinCustomerBase.models.local

import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.JSONCity

class MODELProvince: LocalDataModel {
    var id: Int = -1
    var name: String = ""
    var code: String = ""

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONCity
            id = jsonData.id
            name = jsonData.name
            val codeData = jsonData.code
            if (codeData != null)
                code = codeData
            true
        } catch (exception: Exception) {
            Logger.error(this::class.java.name, "Problem while mapping Province!", exception)
            false
        }
    }

    override fun toString(): String = name.toString()
}