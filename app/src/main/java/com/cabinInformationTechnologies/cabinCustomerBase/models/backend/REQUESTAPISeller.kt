package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class REQUESTAPISeller (@Json(name = "SELLER") val seller: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTSeller>)