package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context

class MODELFilterCategory: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    private var id: Int = -1
    private var name: String = ""
    private var amount: Int? = null
    private var isSelected: Boolean? = null
    private val subFilterCategories: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterCategory?> = mutableListOf()

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONFilterCategory
            this.id = jsonData.id
            this.name = jsonData.name
            this.amount = jsonData.amount
            this.isSelected = jsonData.isSelected
            if (!jsonData.filterCategories.isNullOrEmpty()) {
                jsonData.filterCategories.forEach {
                    val category = com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterCategory()
                    if (category.mapFrom(context, it))
                        subFilterCategories.add(category)
                }
            }
            true
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                context,
                this::class.java.name,
                "Failed to map category!",
                exception)
            false
        }
    }

    fun getId(): Int = id
    fun getName(): String = name
    fun getAmount(): Int? = amount
    fun getIsSelected(): Boolean? = isSelected
    fun getSubCategories(): List<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterCategory?> = subFilterCategories
}