package ist.cabin.cabinCustomerBase.models.local

import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.JSONSeller

class MODELSeller: LocalDataModel {
    lateinit var name: String
    var id: Int? = null

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonModel = modelData as JSONSeller
            name = jsonModel.name
            id = jsonModel.id
            true
        } catch (exception: Exception){
            Logger.warn(this::class.java.name, "A problem occurred while mapping Seller.", exception)
            false
        }
    }
}