package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONOrder

class MODELOrder: LocalDataModel {
    private var id: Int = 0
    private var code: String = ""
    private var price: Double = 0.0
    private var orderDate: String? = null
    private var orderTime: String? = null
    private var paymentType: String? = null

    var sellers: MutableList<MODELOrderSeller?> = mutableListOf()
    var productCount: Int = 0

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONOrder
            this.id = jsonData.id
            this.code = jsonData.orderCode
            jsonData.sellers.forEach {
                val seller = MODELOrderSeller()
                if (seller.mapFrom(context, it))
                    sellers.add(seller)
            }
            this.price = jsonData.price
            this.orderDate = jsonData.orderDate
            this.orderTime = jsonData.orderTime
            this.productCount = jsonData.productCount
            this.paymentType = jsonData.paymentType
            true
        } catch (exception: Exception) {
            Logger.warn(
                context,
                this::class.java.name,
                "Error while mapping order!",
                exception
            )
            false
        }

    }
}