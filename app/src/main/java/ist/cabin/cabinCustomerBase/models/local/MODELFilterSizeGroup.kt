package ist.cabin.cabinCustomerBase.models.local

import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.JSONFilterSizeNameGroup

class MODELFilterSizeGroup: LocalDataModel {
    private var id: Int = -1
    private var name: String = ""
    private var sizes: MutableList<MODELFilterSize?> = mutableListOf()
    private var amount: Int = 0
    private var isSelected: Boolean = false

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONFilterSizeNameGroup
            this.id = jsonData.id
            this.name = jsonData.name
            if (!jsonData.sizes.isNullOrEmpty())
                jsonData.sizes.forEach {
                    val size = MODELFilterSize()
                    if (size.mapFrom(it))
                        this.sizes.add(size)
                }
            val amountData = jsonData.amount
            if (amountData != null)
                this.amount = amountData
            val isSelectedData = jsonData.isSelected
            if (isSelectedData != null)
                this.isSelected = isSelectedData
            true
        } catch (exception: Exception) {
            Logger.error(this::class.java.name, "Failed to map SizeNameGroup!", exception)
            false
        }
    }

    fun getId(): Int = id
    fun getName(): String = name
    fun getSizes(): List<MODELFilterSize?> = sizes
    fun getAmount(): Int = amount
    fun getIsSelected(): Boolean = isSelected
}