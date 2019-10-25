package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class JSONColor (@Json(name = "ID") val id: Int,
                      @Json(name = "NAME") val name: String?,
                      @Json(name = "HEX_CODE") val hexCode: String,
                      @Json(name = "RGB_CODE") val rgbCode: String?,//FIXME: BETTER RGB CODE FROM BACKEND
                      @Json(name = "ISFAVORITE") val isFavorite: Boolean?,
                      @Json(name = "IMAGE") val images: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONImage>?,
                      @Json(name = "SIZE") val sizes: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONSize>
                      )