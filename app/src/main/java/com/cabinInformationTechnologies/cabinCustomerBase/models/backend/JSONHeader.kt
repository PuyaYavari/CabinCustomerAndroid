package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JSONHeader(@Json(name = "ID") val id: Int,
                      @Json(name = "TEXT") val text: String,
                      @Json(name = "SUB_BANNER") val subBanners: List<JSONBannerGroup>?,
                      @Json(name = "MAIN_BANNER") val mainBanner: List<JSONBanner>?)