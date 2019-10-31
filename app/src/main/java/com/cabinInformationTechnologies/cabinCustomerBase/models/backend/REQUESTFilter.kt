package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class REQUESTFilter (@Json(name = "CATEGORY") val category: List<Int?>?,
                          @Json(name = "GENDER") val sexes: List<Int?>?,
                          @Json(name = "SELLER") val sellers: List<Int?>?,
                          @Json(name = "COLOR") val colors: List<Int?>?,
                          @Json(name = "SIZE") val sizes: List<Int?>?,
                          @Json(name = "PRICE") val prices: List<Int?>?)