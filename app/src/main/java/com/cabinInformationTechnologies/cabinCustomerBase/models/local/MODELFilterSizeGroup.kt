package com.cabinInformationTechnologies.cabinCustomerBase.models.local

class MODELFilterSizeGroup: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    private var id: Int = -1
    private var name: String = ""
    private var sizes: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterSize?> = mutableListOf()
    private var amount: Int = 0
    private var isSelected: Boolean = false

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONFilterSizeNameGroup
            this.id = jsonData.id
            this.name = jsonData.name
            if (!jsonData.sizes.isNullOrEmpty())
                jsonData.sizes.forEach {
                    val size = com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterSize()
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
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(this::class.java.name, "Failed to map SizeNameGroup!", exception)
            false
        }
    }

    fun getId(): Int = id
    fun getName(): String = name
    fun getSizes(): List<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterSize?> = sizes
    fun getAmount(): Int = amount
    fun getIsSelected(): Boolean = isSelected
}