package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class JSONProduct (@Json(name = "ID") val id: Int,
                        @Json(name = "CODE") val code: String,
                        @Json(name = "TITLE") val title: String,
                        @Json(name = "PRICE") val price: Double,
                        @Json(name = "AMOUNT") val amount: Int?,
                        @Json(name = "SELLER") val sellerName: List<JSONSellerName>,
                        @Json(name = "SHIPPING_DURATION") val shippingDuration: List<JSONShippingDuration>,
                        @Json(name = "SHIPPING_TYPE") val shippingType: List<JSONShippingType>,
                        @Json(name = "COLOR") val colors: List<JSONColor>)