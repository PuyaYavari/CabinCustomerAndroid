package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class APIUser (@Json(name = "USER") var users: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONUser>?)
