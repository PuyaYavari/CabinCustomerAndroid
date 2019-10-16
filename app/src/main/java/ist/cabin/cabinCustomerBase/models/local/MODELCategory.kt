package ist.cabin.cabinCustomerBase.models.local

import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.JSONCategory

class MODELCategory: LocalDataModel {
    private var id: Int = -1
    private var name: String = ""
    private val subCategories: MutableList<MODELCategory?> = mutableListOf()

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONCategory
            this.id = jsonData.id
            this.name = jsonData.name
            if (!jsonData.categories.isNullOrEmpty()) {
                jsonData.categories.forEach {
                    val category = MODELCategory()
                    if (category.mapFrom(it))
                        subCategories.add(category)
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
    fun getSubCategories(): List<MODELCategory?> = subCategories
}