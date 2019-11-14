package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JSONOrderSeller (@Json(name = "ID") val id: Int,
                            @Json(name = "NAME") val name: String,
                            @Json(name = "PRODUCT") val products: List<JSONProduct?>,
                            @Json(name = "SELLERSHIPPINGPRICE") val shippingPrice: Double?,
                            @Json(name = "SELLERSUBTOTAL") val subtotal: Double?,
                            @Json(name = "SELLERTOTAL") val total: Double,
                            @Json(name = "PHONE") val phone: String,
                            @Json(name = "ADDRESS") val address: String,
                            @Json(name = "RETURNPAYMENT") val returnPayment: String,
                            @Json(name = "DELIVERYDATE") val deliveryDate: String?,
                            @Json(name = "CARGO") val cargo: List<JSONCargo?>?)