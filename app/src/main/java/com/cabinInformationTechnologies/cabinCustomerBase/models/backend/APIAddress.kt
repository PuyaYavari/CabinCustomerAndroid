package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class APIAddress (@Json(name = "ADDRESS") val addresses: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONAddress?>)