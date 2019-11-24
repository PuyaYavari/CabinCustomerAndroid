package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JSONFilterSelecteds (@Json(name = "CATEGORY") val category: List<JSONID?>?,
                                @Json(name = "GENDER") val sexes: List<JSONID?>?,
                                @Json(name = "SELLER") val sellers: List<JSONID?>?,
                                @Json(name = "COLOR") val colors: List<JSONID?>?,
                                @Json(name = "SIZE") val sizes: List<JSONID?>?,
                                @Json(name = "PRICE") val prices: List<JSONID?>?)