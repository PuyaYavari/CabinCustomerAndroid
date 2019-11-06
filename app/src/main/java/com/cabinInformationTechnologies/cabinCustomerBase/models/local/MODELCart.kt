package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONCart

class MODELCart : LocalDataModel {
    private var seller: MutableList<MODELSeller> = mutableListOf()
    private var shippingPrice: Double? = null
    private var subtotal: Double? = null
    private var total: Double = 0.0

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONCart?
            if (jsonData != null) {
                jsonData.seller.forEach {
                    val seller = MODELSeller()
                    if (seller.mapFrom(
                            context,
                            it
                        )
                    )
                        this.seller.add(seller)
                }
                this.shippingPrice = jsonData.shippingPrice
                this.subtotal = jsonData.subtotal
                val totalData = jsonData.total
                if (totalData != null)
                    this.total = totalData
            }
            true
        } catch (exception: Exception) {
            Logger.error(
                context,
                this::class.java.name,
                "Problem while mapping to model.",
                exception)
            false
        }
    }

    fun getSellers() = seller
    fun getShippingPrice() = shippingPrice
    fun getSubtotal() = subtotal
    fun getTotal() = total
}