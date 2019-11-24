package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIOrderId

class MODELOrderID : LocalDataModel {
    private var id: Int = -1

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as APIOrderId
            this.id = jsonData.order[0].id
            true
        } catch (exception: Exception) {
            Logger.warn(
                context,
                this::class.java.name,
                "Error while mapping orderId!",
                exception
            )
            false
        }
    }

    fun getId() = this.id
}