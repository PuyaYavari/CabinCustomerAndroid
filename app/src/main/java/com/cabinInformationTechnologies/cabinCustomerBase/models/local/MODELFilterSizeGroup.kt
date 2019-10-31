package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context

class MODELFilterSizeGroup: LocalDataModel {
    private var id: Int = -1
    private var name: String = ""
    private var sizes: MutableList<MODELFilterSize>? = null
    var amount: Int = 0
    var isSelected: Boolean = false

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONFilterSizeNameGroup
            this.id = jsonData.id
            this.name = jsonData.name
            if (!jsonData.sizes.isNullOrEmpty())
                sizes = mutableListOf()
                jsonData.sizes.forEach {
                    val size = MODELFilterSize()
                    if (size.mapFrom(context, it))
                        this.sizes?.add(size)
                }
            val amountData = jsonData.amount
            if (amountData != null)
                this.amount = amountData
            val isSelectedData = jsonData.isSelected
            if (isSelectedData != null)
                this.isSelected = isSelectedData
            true
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                context,
                this::class.java.name,
                "Failed to map SizeNameGroup!",
                exception)
            false
        }
    }

    fun getId(): Int = id
    fun getName(): String = name
    fun getSizes(): MutableList<MODELFilterSize>? = sizes
    fun setSizes(sizes: MutableList<MODELFilterSize>?) {
        this.sizes = sizes
    }
}