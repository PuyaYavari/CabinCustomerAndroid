package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JSONBanner(@Json(name = "ID") val id: Int,
                      @Json(name = "IMAGE") val image: List<JSONImage>,
                      @Json(name = "FILTER") val filter: List<JSONFilterSelecteds>,
                      @Json(name = "DESCRIPTION") val description: String?)