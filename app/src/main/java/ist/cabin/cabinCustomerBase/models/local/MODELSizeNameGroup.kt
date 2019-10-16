package ist.cabin.cabinCustomerBase.models.local

import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.JSONSizeNameGroup

class MODELSizeNameGroup: LocalDataModel {
    private var id: Int = -1
    private var name: String = ""
    private var sizes: MutableList<MODELSize?> = mutableListOf()

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONSizeNameGroup
            this.id = jsonData.id
            this.name = jsonData.name
            if (!jsonData.sizes.isNullOrEmpty())
                jsonData.sizes.forEach {
                    val size = MODELSize()
                    if (size.mapFrom(it))
                        this.sizes.add(size)
                }
            true
        } catch (exception: Exception) {
            Logger.error(this::class.java.name, "Failed to map SizeNameGroup!", exception)
            false
        }
    }

    fun getId(): Int = id
    fun getName(): String = name
    fun getSizes(): List<MODELSize?> = sizes
}