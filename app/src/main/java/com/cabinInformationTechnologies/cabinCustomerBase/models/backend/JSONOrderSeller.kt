package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JSONOrderSeller (@Json(name = "ID") val id: Int,
                            @Json(name = "NAME") val name: String,
                            @Json(name = "PRODUCT") val products: List<JSONOrderProduct?>,
                            @Json(name = "SHIPPING_PRICE") val shippingPrice: Double?,
                            @Json(name = "SUB_TOTAL_PRICE") val subtotal: Double?,
                            @Json(name = "TOTAL_PRICE") val total: Double,
                            @Json(name = "PHONE") val phone: String?,
                            @Json(name = "ADDRESS") val address: String?,
                            @Json(name = "DELIVERY_DATE") val deliveryDate: String?,
                            @Json(name = "RETURN_PROCEDURE") val returnProcedure: List<JSONReturnProcedure?>?,
                            @Json(name = "CARGO") val cargo: List<JSONCargo?>?)