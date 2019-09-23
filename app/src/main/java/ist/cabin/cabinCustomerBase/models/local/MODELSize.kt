package ist.cabin.cabinCustomerBase.models.local

import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.JSONSize

class MODELSize: LocalDataModel {
    var id: Int = -1
    lateinit var name: String

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONSize
            id = jsonData.id
            name = jsonData.name
            true
        } catch (exception: Exception) {
            Logger.warn(this::class.java.name, "A problem occurred while mapping Size.", exception)
            false
        }

    }
}