package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class REQUESTSeller (@Json(name = "ID") val id: Int,
                          @Json(name = "PRODUCT") val products: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTProduct>?)