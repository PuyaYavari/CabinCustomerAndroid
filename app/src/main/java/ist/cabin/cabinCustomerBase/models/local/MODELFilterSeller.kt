package ist.cabin.cabinCustomerBase.models.local

import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.JSONFilterSeller

class MODELFilterSeller: LocalDataModel {
    lateinit var name: String
    var id: Int? = null
    var amount: Int = 0
    var isSelected: Boolean = false

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONFilterSeller
            name = jsonData.name
            id = jsonData.id
            amount = jsonData.amount
            isSelected = jsonData.isSelected
            true
        } catch (exception: Exception){
            Logger.warn(this::class.java.name, "A problem occurred while mapping Seller.", exception)
            false
        }
    }
}