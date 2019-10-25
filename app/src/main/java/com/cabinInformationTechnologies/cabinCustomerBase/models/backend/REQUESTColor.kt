package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class REQUESTColor (@Json(name = "ID") val id: Int,
                         @Json(name = "SIZE") val sizes: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTSize>?
)