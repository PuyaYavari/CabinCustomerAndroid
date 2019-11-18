package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JSONOrderProduct (@Json(name = "ID") val id: Int,
                             @Json(name = "CODE") val code: String,
                             @Json(name = "PRICE") val price: Double,
                             @Json(name = "COLOR") val colors: List<JSONColorName>,
                             @Json(name = "SIZE") val sizes: List<JSONSize>,
                             @Json(name = "TITLE") val title: String?,
                             @Json(name = "AMOUNT") val amount: Int?,
                             @Json(name = "SELLER") val sellerName: List<JSONSellerName>?,
                             @Json(name = "SHIPPING_DURATION") val shippingDuration: List<JSONShippingDuration>?,
                             @Json(name = "SHIPPING_TYPE") val shippingType: List<JSONShippingType>?,
                             @Json(name = "IMAGE") val images: List<JSONURL>)