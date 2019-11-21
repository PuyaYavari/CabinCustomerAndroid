package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class JSONImage(@Json(name = "URL") val url: String,
                     @Json(name = "EXTENSION") val extension: String?,//FIXME: MUST BE NON NULLABLE
                     @Json(name = "PRIORITY") val priority: Boolean?)