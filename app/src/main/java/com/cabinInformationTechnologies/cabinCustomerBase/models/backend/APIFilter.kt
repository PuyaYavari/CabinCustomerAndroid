package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class APIFilter(@Json(name = "FILTER") val filters: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONFilter?>)