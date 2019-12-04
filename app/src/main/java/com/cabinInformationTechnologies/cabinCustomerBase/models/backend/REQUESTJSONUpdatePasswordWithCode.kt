package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class REQUESTJSONUpdatePasswordWithCode (@Json(name = "PASSWORD") val password: String,
                                              @Json(name = "CHANGE_PASSWORD_CODE") val code: String,
                                              @Json(name = "USERNAME") val email: String)