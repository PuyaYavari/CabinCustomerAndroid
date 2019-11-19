package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIOrders

class MODELOrders: LocalDataModel {
    var pending: MutableList<MODELOrder?> = mutableListOf()
    var shipped: MutableList<MODELOrder?> = mutableListOf()
    var sent: MutableList<MODELOrder?> = mutableListOf()

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        try {
            val jsonData = modelData as APIOrders
            val ordersData = jsonData.orders?.get(0)
            if (ordersData != null) {
                ordersData.pending.forEach {
                    val order = MODELOrder()
                    if (order.mapFrom(context, it))
                        pending.add(order)
                }
                ordersData.shipped.forEach {
                    val order = MODELOrder()
                    if (order.mapFrom(context, it))
                        shipped.add(order)
                }
                ordersData.sent.forEach {
                    val order = MODELOrder()
                    if (order.mapFrom(context, it))
                        sent.add(order)
                }
                return true
            }
            return false
        } catch (exception: Exception) {
            Logger.warn(
                context,
                this::class.java.name,
                "Error while mapping orders!",
                exception
            )
            return false
        }
    }
}