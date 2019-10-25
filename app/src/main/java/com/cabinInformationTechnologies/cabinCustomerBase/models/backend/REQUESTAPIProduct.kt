package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class REQUESTAPIProduct(@Json(name = "PRODUCT") val product: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTProduct>)