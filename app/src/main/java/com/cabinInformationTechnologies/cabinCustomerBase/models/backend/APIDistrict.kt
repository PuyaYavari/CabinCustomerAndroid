package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class APIDistrict (@Json(name = "DISTRICT") val districts: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONDistrict?>)