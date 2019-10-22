package ist.cabin.cabinCustomerBase.models.local

import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.JSONFilterPrice

class MODELFilterPrice: LocalDataModel {
    private var id: Int = -1
    private var name: String = ""
    private var amount: Int = 0
    private var isSelected: Boolean = false

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONFilterPrice
            this.id = jsonData.id
            this.name = jsonData.name
            this.amount = jsonData.amount
            this.isSelected = jsonData.isSelected
            true
        } catch (exception: Exception) {
            Logger.error(this::class.java.name, "Failed to map PriceInterval!", exception)
            false
        }
    }

    fun getId(): Int = id
    fun getName(): String = name
    fun getAmount(): Int = amount
    fun getIsSelected(): Boolean = isSelected
}