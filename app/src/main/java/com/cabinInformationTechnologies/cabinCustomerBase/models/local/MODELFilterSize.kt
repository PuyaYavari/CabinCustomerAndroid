package com.cabinInformationTechnologies.cabinCustomerBase.models.local

class MODELFilterSize: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    private var id: Int = -1
    private var name: String = ""
    private var amount: Int = 0
    private var isSelected: Boolean = false

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONFilterSize
            id = jsonData.id
            name = jsonData.name
            amount = jsonData.amount
            isSelected = jsonData.isSelected
            true
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "A problem occurred while mapping Size.", exception)
            false
        }

    }

    fun getId() = id
    fun getName() = name
    fun getAmount() = amount
    fun getIsSelected() = isSelected
}