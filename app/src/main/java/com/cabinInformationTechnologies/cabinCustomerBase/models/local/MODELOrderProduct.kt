package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONOrderProduct

class MODELOrderProduct: LocalDataModel {
    private var id: Int = 0
    private var code: String = ""
    private var price: Double = 0.0
    private var colorName: String = ""
    private var colorId: Int = 0
    private var sizeName: String = ""
    private var sizeId: Int = 0
    private var title: String? = null
    private var amount: Int = 0
    private var shippingDurationText: String = ""
    private var shippingDurationId: Int = 0
    private var shippingTypeName: String = ""
    private var shippingTypeId: Int = 0
    private var imageURLs: MutableList<String> = mutableListOf()

    override fun <T> mapFrom(context: Context, modelData: T): Boolean { //FIXME: REMOVE IF NON NECESSARY FIELDS
        return try {
            val jsonData = modelData as JSONOrderProduct
            this.id = jsonData.id
            this.code = jsonData.code
            this.price = jsonData.price
            this.colorName = jsonData.colors[0].name
            this.colorId = jsonData.colors[0].id
            this.sizeName = jsonData.sizes[0].name
            this.sizeId = jsonData.sizes[0].id
            this.title = jsonData.title
            val amount = jsonData.amount
            if (amount != null)
                this.amount = amount
            val shippingDurationText = jsonData.shippingDuration?.get(0)?.name
            if (shippingDurationText != null)
                this.shippingDurationText = shippingDurationText
            val shippingDurationId = jsonData.shippingDuration?.get(0)?.id
            if (shippingDurationId != null)
                this.shippingDurationId = shippingDurationId
            val shippingTypeName = jsonData.shippingType?.get(0)?.name
            if (shippingTypeName != null)
                this.shippingTypeName = shippingTypeName
            val shippingTypeId = jsonData.shippingType?.get(0)?.id
            if (shippingTypeId != null)
                this.shippingTypeId = shippingTypeId
            jsonData.images?.forEach {
                imageURLs.add(it.url)
            }
            true
        } catch (exception: Exception) {
            Logger.warn(
                context,
                this::class.java.name,
                "Error while mapping order product!",
                exception
            )
            false
        }
    }

    fun getId() = id
    fun getCode() = code
    fun getPrice() = price
    fun getColorName() = colorName
    fun getColorId() = colorId
    fun getSizeName() = sizeName
    fun getSizeId() = sizeId
    fun getTitle() = title
    fun getAmount() = amount
    fun getShippingDurationText() = shippingDurationText
    fun getShippingDurationId() = shippingDurationId
    fun getShippingTypeName() = shippingTypeName
    fun getShippingTypeId() = shippingTypeId
    fun getImageURLs() = imageURLs
}