package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JSONOrders (@Json(name = "PENDING") val pending: List<JSONOrder?>?,
                       @Json(name = "SHIPPED") val shipped: List<JSONOrder?>?,
                       @Json(name = "SENT") val sent: List<JSONOrder?>?)