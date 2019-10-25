package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class REQUESTAPIRegister (@Json(name = "USER") val user: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTRegister>)