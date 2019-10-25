package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class APICity (@Json(name = "CITY") val cities: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONCity?>)