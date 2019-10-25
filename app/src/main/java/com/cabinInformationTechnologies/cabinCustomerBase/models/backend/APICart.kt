package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class APICart (@Json(name = "BASKET") val cart: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONCart?>?)