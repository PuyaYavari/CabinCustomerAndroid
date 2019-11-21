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
    private var deliveryDate: Date? = null
    private var returable: Boolean = false
    private var returnPayment: String = ""
    private var returnRemainingDay: String = ""
    private var returnDescription: String = ""
    private var returnProcedure: MutableList<String> = mutableListOf()
    private var cargo: MutableList<MODELCargo?> = mutableListOf()

    var products: MutableList<MODELOrderProduct?> = mutableListOf()

    @SuppressLint("SimpleDateFormat")
    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONOrderSeller
            this.id = jsonData.id
            this.name = jsonData.name
            jsonData.products.forEach {
                val product = MODELOrderProduct()
                if (product.mapFrom(context, it))
                    this.products.add(product)
            }
            this.shippingPrice = jsonData.shippingPrice
            this.subtotal = jsonData.subtotal
            this.total = jsonData.total
            val phoneData = jsonData.phone
            if (phoneData != null)
                this.phone = phoneData
            val addressData = jsonData.address
            if (addressData != null)
                this.address = addressData
            val deliveryDateData = jsonData.deliveryDate
            if (deliveryDateData != null)
                deliveryDate = SimpleDateFormat("yyyy-MM-dd").parse(deliveryDateData)
            if (deliveryDate != null) {
                val deliveryYear = deliveryDate?.year
                if (deliveryYear != null)
                    deliveryDate?.year = deliveryYear + 1900
            }
            if (jsonData.returnProcedure != null) {
                this.returable = true
                val returnRemainingDay = jsonData.returnProcedure.get(0)?.remainingDay
                if (returnRemainingDay != null)
                    this.returnRemainingDay = returnRemainingDay
                val returnPaymentData = jsonData.returnProcedure.get(0)?.payment
                if (returnPaymentData != null)
                    this.returnPayment = returnPaymentData
                val returnDescription = jsonData.returnProcedure.get(0)?.description
                if (returnDescription != null)
                    this.returnDescription = returnDescription
                jsonData.returnProcedure.get(0)?.steps?.forEach {
                    if (it != null) {
                        val description = it.descriptor
                        if (description != null)
                            returnProcedure.add(description)
                    }
                }
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

    fun getId() = this.id
    fun getName() = this.name
    fun getShippingPrice() = this.shippingPrice
    fun getSubtotal() = this.subtotal
    fun getTotal() = this.total
    fun getPhone() = this.phone
    fun getAddress() = this.address
    fun isReturnable() = this.returable
    fun getReturnPayment() = this.returnPayment
    fun getDeliveryDate() = this.deliveryDate
    fun getReturnDescription() = this.returnDescription
    fun getReturnProcedure() = this.returnProcedure
    fun getReturnRemainingDay() = this.returnRemainingDay
    fun getCargo() = this.cargo
}