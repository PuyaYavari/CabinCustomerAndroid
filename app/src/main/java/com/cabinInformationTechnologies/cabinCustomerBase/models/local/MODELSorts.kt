package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APISortOptions

class MODELSorts : LocalDataModel {
    val sorts: MutableList<MODELSort?> = mutableListOf()

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as APISortOptions
            jsonData.sorts?.forEach {
                val sort = MODELSort()
                if(sort.mapFrom(context, it))
                    sorts.add(sort)
            }
            true
        } catch (exception: Exception) {
            Logger.error(
                context,
                this::class.java.name,
                "Error while mapping sorts!!",
                exception
            )
            false
        }
    }
}