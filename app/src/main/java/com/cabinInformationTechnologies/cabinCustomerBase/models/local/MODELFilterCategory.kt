package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONFilterCategory

class MODELFilterCategory: LocalDataModel {
    private var id: Int = -1
    private var name: String = ""
    private var amount: Int? = null
    private var isSelected: Boolean? = null
    private val subFilterCategories: MutableList<MODELFilterCategory?> = mutableListOf()

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONFilterCategory
            this.id = jsonData.id
            this.name = jsonData.name
            this.amount = jsonData.amount
            this.isSelected = jsonData.isSelected
            if (!jsonData.filterCategories.isNullOrEmpty()) {
                jsonData.filterCategories.forEach {
                    val category = MODELFilterCategory()
                    if (category.mapFrom(context, it))
                        subFilterCategories.add(category)
                }
            }
            true
        } catch (exception: Exception) {
            Logger.error(
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
    fun getSubCategories(): MutableList<MODELFilterCategory?> = subFilterCategories
}