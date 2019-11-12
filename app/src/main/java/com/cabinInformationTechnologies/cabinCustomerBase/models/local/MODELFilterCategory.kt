package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONFilterCategory
import com.cabinInformationTechnologies.cabinCustomerBase.threeStateSelection

class MODELFilterCategory: LocalDataModel {
    private var id: Int = -1
    private var name: String = ""
    private var amount: Int? = null
    var isSelected: Boolean? = null
    var state: threeStateSelection? = null
    private var subFilterCategories: MutableList<MODELFilterCategory>? = null
    private var isLeaf = false

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONFilterCategory
            this.id = jsonData.id
            this.name = jsonData.name
            this.amount = jsonData.amount
            if (!jsonData.filterCategories.isNullOrEmpty()) {
                subFilterCategories = mutableListOf()
                state = threeStateSelection.UNSELECTED
                var selectedSubSizeCount = 0
                jsonData.filterCategories.forEach {
                    val category = MODELFilterCategory()
                    if (category.mapFrom(context, it)) {
                        subFilterCategories!!.add(category)
                        val subCatIsSelected = category.isSelected
                        val subCatState = category.state
                        if (subCatIsSelected != null && subCatIsSelected) {
                            state = threeStateSelection.HALFSELECTED
                            selectedSubSizeCount++
                        }
                        if (subCatState != null) {
                            if (subCatState == threeStateSelection.HALFSELECTED) {
                                state = threeStateSelection.HALFSELECTED
                            } else if (subCatState == threeStateSelection.SELECTED) {
                                state = threeStateSelection.HALFSELECTED
                                selectedSubSizeCount++
                            }
                        }
                    }
                }
                if (selectedSubSizeCount == subFilterCategories!!.size)
                    state = threeStateSelection.SELECTED
            } else {
                this.isLeaf = true
                this.isSelected = jsonData.isSelected
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
    fun getSubCategories(): MutableList<MODELFilterCategory>? = subFilterCategories
    fun getIsLeaf() = isLeaf

    fun setSubCategories(subCategories: MutableList<MODELFilterCategory>) {
        this.subFilterCategories = subCategories
    }
}