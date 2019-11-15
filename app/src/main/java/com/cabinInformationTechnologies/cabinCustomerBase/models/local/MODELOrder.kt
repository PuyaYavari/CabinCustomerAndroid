package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.annotation.SuppressLint
import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONOrder
import java.text.SimpleDateFormat
import java.util.*

class MODELOrder: LocalDataModel {
    private var id: Int = 0
    private var code: String = ""
    private var price: Double = 0.0
    private var orderDate: Date? = null
    private var orderTime: String? = null
    private var paymentType: String? = null

    var sellers: MutableList<MODELOrderSeller?> = mutableListOf()
    var productCount: Int = 0

    @SuppressLint("SimpleDateFormat")
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
            val orderDateData = jsonData.orderDate
            if (orderDateData != null)
                orderDate = SimpleDateFormat("yyyy-MM-dd").parse(orderDateData)
            if (orderDate != null) {
                val orderYear = orderDate?.year
                if (orderYear != null)
                    orderDate?.year = orderYear + 1900
            }
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

    fun getId() = id
    fun getCode() = code
    fun getPrice() = price
    fun getOrderDate() = orderDate
    fun getOrderTime() = orderTime
    fun getPaymentType() = paymentType
}