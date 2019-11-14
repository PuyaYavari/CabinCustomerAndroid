package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JSONOrder (@Json(name = "ID") val id: Int,
                      @Json(name = "CODE") val orderCode: String,
                      @Json(name = "SELLER") val sellers: List<JSONOrderSeller>,
                      @Json(name = "PRICE") val price: Double,
                      @Json(name = "ORDER_DATE") val orderDate: String?,
                      @Json(name = "ORDER_TIME") val orderTime: String?,
                      @Json(name = "PRODUCT_COUNT") val productCount: Int,
                      @Json(name = "PAYMENT_TYPE") val paymentType: String?)