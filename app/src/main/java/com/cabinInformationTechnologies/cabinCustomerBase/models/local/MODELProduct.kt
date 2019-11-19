package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class MODELProduct : LocalDataModel, Parcelable{

    private var id: Int = -1
    private lateinit var sellerName: String
    private lateinit var productName: String
    private lateinit var productId: String
    private var price: Double? = null
    private var amount: Int? = null
    private var cargoDurationId: Int? = null
    private lateinit var cargoDuration: String
    private var cargoTypeId: Int? = null
    private lateinit var cargoType: String
    private var colors: MutableList<MODELColor> = mutableListOf()

    @Throws(java.lang.Exception::class)
    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonModel = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONProduct
            val seller = MODELSellerName()
            id = jsonModel.id
            if (seller.mapFrom(context, jsonModel.sellerName[0]))
                sellerName = seller.name
            productName = jsonModel.title
            productId = jsonModel.code
            price = jsonModel.price
            amount = jsonModel.amount

            val cargoDurationData = jsonModel.shippingDuration?.get(0) ?: throw java.lang.Exception("Cargo Duration Not Mapped.")
            cargoDurationId = cargoDurationData.id
            cargoDuration = cargoDurationData.name

            val cargoTypeData = jsonModel.shippingType[0] ?: throw java.lang.Exception("Cargo Type Not Mapped.")
            cargoTypeId = cargoTypeData.id
            cargoType = cargoTypeData.name

            jsonModel.colors.forEach {
                val color = com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor()
                if (color.mapFrom(context, it))
                    colors.add(color)
            }
            true
        } catch (exception: Exception){
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                context,
                this::class.java.name,
                "A problem occurred while mapping Product.",
                exception)
            false
        }

    }

    fun incAmount(count: Int){
        var amount = this.amount
        if (amount != null)
            amount += count
        this.amount = amount
    }

    fun decAmount(count: Int){
        var amount = this.amount
        if (amount != null)
            amount -= count
        this.amount = amount
    }

    fun getId() = this.id
    fun getSellerName() = this.sellerName
    fun getProductName() = this.productName
    fun getProductId() = this.productId
    fun getPrice() = this.price
    fun getAmount() = this.amount
    fun getCargoDurationId() = this.cargoDurationId
    fun getCargoDuration() = this.cargoDuration
    fun getCargoTypeId() = this.cargoTypeId
    fun getCargoType() = this.cargoType
    fun getColors() = this.colors

    fun setAmount(amount: Int?) { this.amount = amount }

    fun setAll(
        id: Int,
        sellerName: String,
        productName: String,
        productId: String,
        price: Double?,
        amount: Int?,
        cargoDurationId: Int?,
        cargoDuration: String,
        cargoTypeId: Int?,
        cargoType: String,
        colors: MutableList<MODELColor>
    ) {
        this.id = id
        this.sellerName = sellerName
        this.productName = productName
        this.productId = productId
        this.price = price
        this.amount = amount
        this.cargoDurationId = cargoDurationId
        this.cargoDuration = cargoDuration
        this.cargoTypeId = cargoTypeId
        this.cargoType = cargoType
        this.colors = colors
    }
}
