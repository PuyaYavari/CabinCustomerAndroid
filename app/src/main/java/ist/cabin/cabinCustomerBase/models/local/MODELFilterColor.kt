package ist.cabin.cabinCustomerBase.models.local

import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.JSONFilterColor

class MODELFilterColor: LocalDataModel {
    private var id: Int = -1
    private var name: String = ""
    var hexCode: String = ""
    var rgb: String? = null
    private var amount: Int = 0
    private var isSelected: Boolean = false

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONFilterColor
            this.id = jsonData.id
            val nameData = jsonData.name
            if (nameData != null)
                this.name = nameData
            this.hexCode = jsonData.hexCode
            this.rgb = jsonData.rgbCode
            this.amount = jsonData.amount
            this.isSelected = jsonData.isSelected
            true
        } catch (exception: Exception) {
            Logger.error(this::class.java.name, "Failed to map rawColor!", exception)
            false
        }
    }

    fun getId(): Int = id
    fun getName(): String = name
    fun getAmount(): Int = amount
    fun getIsSelected(): Boolean = isSelected
}