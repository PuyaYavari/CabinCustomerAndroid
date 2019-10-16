package ist.cabin.cabinCustomerBase.models.local

import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.JSONPriceInterval

class MODELPriceInterval: LocalDataModel {
    private var id: Int = -1
    private var name: String = ""

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONPriceInterval
            this.id = jsonData.id
            this.name = jsonData.name
            true
        } catch (exception: Exception) {
            Logger.error(this::class.java.name, "Failed to map PriceInterval!", exception)
            false
        }
    }

    fun getId(): Int = id
    fun getName(): String = name
}