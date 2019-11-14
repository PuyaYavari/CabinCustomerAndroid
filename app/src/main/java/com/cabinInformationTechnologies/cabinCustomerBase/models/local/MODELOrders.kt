package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONOrders

class MODELOrders: LocalDataModel {
    var pending: MutableList<MODELOrder?> = mutableListOf()
    var shipped: MutableList<MODELOrder?> = mutableListOf()
    var sent: MutableList<MODELOrder?> = mutableListOf()

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsondata = modelData as JSONOrders
            jsondata.pending.forEach {
                val order = MODELOrder()
                if (order.mapFrom(context, it))
                    pending.add(order)
            }
            jsondata.shipped.forEach {
                val order = MODELOrder()
                if (order.mapFrom(context, it))
                    shipped.add(order)
            }
            jsondata.sent.forEach {
                val order = MODELOrder()
                if (order.mapFrom(context, it))
                    sent.add(order)
            }
            true
        } catch (exception: Exception) {
            Logger.warn(
                context,
                this::class.java.name,
                "Error while mapping orders!",
                exception
            )
            false
        }
    }
}