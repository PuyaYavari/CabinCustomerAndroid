package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class REQUESTAnnouncemetPref(@Json(name = "ID") val id: Int,
                                  @Json(name = "STATE") val state: Boolean?)