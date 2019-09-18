package ist.cabin.cabinCustomerBase.models.local

import android.util.Log
import ist.cabin.cabinCustomerBase.models.backend.JSONSize

class MODELSize: LocalDataModel {
    var id: Int? = null
    lateinit var name: String

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONSize
            id = jsonData.id
            name = jsonData.name
            true
        } catch (exception: Exception) {
            Log.e("Size Mapper", exception.message.toString())
            false
        }

    }
}