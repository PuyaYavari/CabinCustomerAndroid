package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JSONHeader(@Json(name = "ID") val id: Int,
                      @Json(name = "DESCRIPTION") val text: String,
                      @Json(name = "BANNER_GROUP") val subBanners: List<JSONBannerGroup>?,
                      @Json(name = "MAIN_BANNER") val mainBanner: List<JSONBanner>?)