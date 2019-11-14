package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JSONCargo (@Json(name = "ID") val id: Int,
                      @Json(name = "NAME") val name: String,
                      @Json(name = "LOGO_URL") val logoUrl: String?,
                      @Json(name = "TRACKING_CODE") val trackingCode: String)