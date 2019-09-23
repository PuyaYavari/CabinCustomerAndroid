package ist.cabin.cabinCustomerBase.models.local

import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.JSONImage

class MODELImage: LocalDataModel {
    lateinit var url: String
    var priority: Boolean = false

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            if (modelData == null)
                Logger.info(this::class.java.name, "Image is null.", null)
            val jsonModel = modelData as JSONImage
            url = jsonModel.url
            if (jsonModel.priority != null)
                priority = jsonModel.priority
            true
        } catch (exception : Exception){
            Logger.warn(this::class.java.name, "A problem occurred while mapping Image.", exception)
            false
        }
    }
}