package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class REQUESTDistrict(@Json(name = "ID") val id: Int,
                           @Json(name = "CODE") val code: String)