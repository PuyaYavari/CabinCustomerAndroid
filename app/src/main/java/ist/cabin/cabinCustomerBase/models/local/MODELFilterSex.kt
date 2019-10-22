package ist.cabin.cabinCustomerBase.models.local

import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.baseAbstracts.Sex
import ist.cabin.cabinCustomerBase.models.backend.JSONFilterSex

class MODELFilterSex: LocalDataModel {
    private var id : Int = -1
    private var name: String? = null
    private var amount: Int? = null
    private var isSelected: Boolean? = null

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONFilterSex
            id = jsonData.id
            name = jsonData.name
            amount = jsonData.amount
            isSelected = jsonData.isSelected
            true
        } catch (exception: Exception) {
            Logger.warn(this::class.java.name, "A problem occurred while mapping sex.", exception)
            false
        }
    }

    fun getId() = id
    fun getName() = name

    fun setSex(id: Int) {
        when (id) {
            Sex.MAN -> {
                this.id = Sex.MAN
                this.name = "Erkek"
            }
            Sex.WOMAN -> {
                this.id = Sex.WOMAN
                this.name = "Kadın"
            }
            else -> {
                this.id = -1
                this.name = null
            }
        }
    }
}