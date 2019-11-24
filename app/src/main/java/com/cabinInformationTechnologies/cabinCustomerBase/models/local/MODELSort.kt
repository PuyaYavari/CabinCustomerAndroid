package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONSortOptions

class MODELSort : LocalDataModel {
    private var id: Int = -1
    private var name: String = ""

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONSortOptions
            this.id = jsonData.id
            this.name = jsonData.name
            true
        } catch (exception: Exception) {
            Logger.error(
                context,
                this::class.java.name,
                "Error while mapping sort!!",
                exception
            )
            false
        }
    }

    override fun toString(): String {
        return this.name
    }

    fun getId() = id
    fun getName() = name
}