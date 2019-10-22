package ist.cabin.cabinCustomerBase.models.local

import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.JSONFilterCategory

class MODELFilterCategory: LocalDataModel {
    private var id: Int = -1
    private var name: String = ""
    private var amount: Int? = null
    private var isSelected: Boolean? = null
    private val subFilterCategories: MutableList<MODELFilterCategory?> = mutableListOf()

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONFilterCategory
            this.id = jsonData.id
            this.name = jsonData.name
            this.amount = jsonData.amount
            this.isSelected = jsonData.isSelected
            if (!jsonData.filterCategories.isNullOrEmpty()) {
                jsonData.filterCategories.forEach {
                    val category = MODELFilterCategory()
                    if (category.mapFrom(it))
                        subFilterCategories.add(category)
                }
            }
            true
        } catch (exception: Exception) {
            Logger.error(this::class.java.name, "Failed to map category!", exception)
            false
        }
    }

    fun getId(): Int = id
    fun getName(): String = name
    fun getAmount(): Int? = amount
    fun getIsSelected(): Boolean? = isSelected
    fun getSubCategories(): List<MODELFilterCategory?> = subFilterCategories
}