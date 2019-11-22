package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class REQUESTGoogleLogin (@Json(name = "GOOGLE_ID") val id: String,
                               @Json(name = "USERNAME") val email: String,
                               @Json(name = "NAME") val name: String?,
                               @Json(name = "SURNAME") val surname: String?)