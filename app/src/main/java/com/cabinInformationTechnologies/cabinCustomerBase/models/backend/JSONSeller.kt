package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class JSONSeller(@Json(name = "ID") val id: Int,
                      @Json(name = "NAME") val name: String,
                      @Json(name = "PRODUCT") val products: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONProduct?>,
                      @Json(name = "SELLERSHIPPINGPRICE") val shippingPrice: Double?,
                      @Json(name = "SELLERSUBTOTAL") val subtotal: Double?,
                      @Json(name = "SELLERTOTAL") val total: Double)