package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context

class MODELFilterSex: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    private var id : Int = -1
    private var name: String? = null
    private var amount: Int? = null
    private var isSelected: Boolean? = null

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONFilterSex
            id = jsonData.id
            name = jsonData.name
            amount = jsonData.amount
            isSelected = jsonData.isSelected
            true
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                context,
                this::class.java.name,
                "A problem occurred while mapping sex.",
                exception)
            false
        }
    }

    fun getId() = id
    fun getName() = name
    fun getAmount() = amount
    fun getIsSelected(): Boolean = isSelected ?: false

    fun setSex(id: Int) {
        when (id) {
            com.cabinInformationTechnologies.cabinCustomerBase.baseAbstracts.Sex.MAN -> {
                this.id = com.cabinInformationTechnologies.cabinCustomerBase.baseAbstracts.Sex.MAN
                this.name = "Erkek"
            }
            com.cabinInformationTechnologies.cabinCustomerBase.baseAbstracts.Sex.WOMAN -> {
                this.id = com.cabinInformationTechnologies.cabinCustomerBase.baseAbstracts.Sex.WOMAN
                this.name = "Kadın"
            }
            else -> {
                this.id = -1
                this.name = null
            }
        }
    }
}