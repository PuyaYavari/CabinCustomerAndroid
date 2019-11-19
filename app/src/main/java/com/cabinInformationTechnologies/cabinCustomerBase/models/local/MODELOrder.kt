package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.annotation.SuppressLint
import android.content.Context
import android.os.Parcelable
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONOrder
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class MODELOrder: LocalDataModel, Parcelable {
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
            val codeData = jsonData.orderCode
            if (codeData != null)
                this.code = codeData
            jsonData.sellers.forEach {
                val seller = MODELOrderSeller()
                if (seller.mapFrom(context, it))
                    sellers.add(seller)
            }
            this.price = jsonData.price
//            val orderDateData = jsonData.orderDate FIXME: UNCOMMENT
//            if (orderDateData != null) FIXME: UNCOMMENT
//                orderDate = SimpleDateFormat("yyyy-MM-dd").parse(orderDateData) FIXME: UNCOMMENT
            if (orderDate != null) {
                val orderYear = orderDate?.year
                if (orderYear != null)
                    orderDate?.year = orderYear + 1900
            }
            this.orderTime = jsonData.orderTime
            val productCount = jsonData.productCount
            if (productCount != null)
                this.productCount = productCount
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