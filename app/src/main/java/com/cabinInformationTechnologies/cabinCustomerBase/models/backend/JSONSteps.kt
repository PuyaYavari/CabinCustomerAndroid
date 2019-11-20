package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JSONSteps(@Json(name = "STEP") val step: Int,
                     @Json(name = "DESCRIPTION") val descriptor: String?)