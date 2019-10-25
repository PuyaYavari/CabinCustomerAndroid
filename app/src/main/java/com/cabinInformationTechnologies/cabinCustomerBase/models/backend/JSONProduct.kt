package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class JSONProduct (@Json(name = "ID") val id: Int,
                        @Json(name = "CODE") val code: String,
                        @Json(name = "TITLE") val title: String,
                        @Json(name = "PRICE") val price: Double,
                        @Json(name = "AMOUNT") val amount: Int?,
                        @Json(name = "SELLER") val sellerName: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONSellerName>,
                        @Json(name = "SHIPPING_DURATION") val shippingDuration: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONShippingDuration>,
                        @Json(name = "SHIPPING_TYPE") val shippingType: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONShippingType>,
                        @Json(name = "COLOR") val colors: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONColor>)