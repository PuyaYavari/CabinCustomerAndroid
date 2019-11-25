package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JSONBannerGroup (@Json(name = "ID") val id: Int,
                            @Json(name = "TEXT") val text: String?,
                            @Json(name = "COMMERCIAL") val commercial: Boolean?,
                            @Json(name = "BANNERS") val banners: List<JSONBanner>)