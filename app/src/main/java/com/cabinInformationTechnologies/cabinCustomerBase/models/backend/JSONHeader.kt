package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JSONHeader(@Json(name = "ID") val id: Int,
                      @Json(name = "NAME") val name: String,
                      @Json(name = "SUB_BANNER") val subBanners: List<JSONBanner>?,
                      @Json(name = "MAIN_BANNER") val mainBanner: List<JSONBanner>?,
                      @Json(name = "IMAGE") val image: List<JSONImage>,
                      @Json(name = "HEADER") val header: List<JSONHeader>?)