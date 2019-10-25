package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class APIProduct (@Json(name = "PRODUCT") val products: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONProduct?>)