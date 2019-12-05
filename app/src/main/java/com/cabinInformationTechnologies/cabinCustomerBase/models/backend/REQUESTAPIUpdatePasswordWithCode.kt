package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class REQUESTAPIUpdatePasswordWithCode (@Json(name = "USER") val user: List<REQUESTJSONUpdatePasswordWithCode?>)