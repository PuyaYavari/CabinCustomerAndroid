package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIHeader

class MODELHeaders: LocalDataModel {
    private var headers: MutableList<MODELHeader> = mutableListOf()

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as APIHeader
            jsonData.header.forEach {
                val header = MODELHeader()
                if (header.mapFrom(context, it))
                    this.headers.add(header)
            }
            true
        } catch (exception: Exception) {
            Logger.warn(
                context,
                this::class.java.name,
                "Unable to map headers!",
                exception
            )
            false
        }
    }

    fun getHeaders() = this.headers
}