package ist.cabin.cabinCustomerBase.models.local

import android.util.Log
import ist.cabin.cabinCustomerBase.models.backend.JSONImage

class MODELImage: LocalDataModel {
    lateinit var url: String
    var priority: Boolean = false

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonModel = modelData as JSONImage
            url = jsonModel.url
            if (jsonModel.priority != null)
                priority = jsonModel.priority
            true
        } catch (exception : Exception){
            Log.e("Image Mapper", exception.message.toString())
            false
        }
    }
}