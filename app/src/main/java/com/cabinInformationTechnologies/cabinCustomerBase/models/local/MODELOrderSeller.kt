package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.annotation.SuppressLint
import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONOrderSeller
import java.text.SimpleDateFormat
import java.util.*

class MODELOrderSeller: LocalDataModel {
    private var id: Int = 0
    private var name: String = ""
    private var shippingPrice: Double? = null
    private var subtotal: Double? = null
    private var total: Double = 0.0
    private var phone: String = ""
    private var address: String = ""
    private var returnPayment: String = ""
    private var deliveryDate: Date? = null
    private var cargo: MutableList<MODELCargo?> = mutableListOf()

    var products: MutableList<MODELProduct?> = mutableListOf()

    @SuppressLint("SimpleDateFormat")
    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONOrderSeller
            this.id = jsonData.id
            this.name = jsonData.name
            jsonData.products.forEach {
                val product = MODELProduct()
                if (product.mapFrom(context, it))
                    this.products.add(product)
            }
            this.shippingPrice = jsonData.shippingPrice
            this.subtotal = jsonData.subtotal
            this.total = jsonData.total
            this.phone = jsonData.phone
            this.address = jsonData.address
            this.returnPayment = jsonData.returnPayment
            val deliveryDateData = jsonData.deliveryDate
            if (deliveryDateData != null)
                deliveryDate = SimpleDateFormat("yyyy-MM-dd").parse(deliveryDateData)
            if (deliveryDate != null) {
                val deliveryYear = deliveryDate?.year
                if (deliveryYear != null)
                    deliveryDate?.year = deliveryYear + 1900
            }
            jsonData.cargo?.forEach {
                val cargo = MODELCargo()
                if (cargo.mapFrom(context, it))
                    this.cargo.add(cargo)
            }
            true
        } catch (exception: Exception) {
            Logger.warn(
                context,
                this::class.java.name,
                "Error while mapping order seller!",
                exception
            )
            false
        }
    }
}