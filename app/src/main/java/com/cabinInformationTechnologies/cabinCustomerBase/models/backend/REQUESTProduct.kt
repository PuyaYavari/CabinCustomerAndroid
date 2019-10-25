package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class REQUESTProduct(
    @Json(name = "ID") val id: Int,
    @Json(name = "AMOUNT") val amount: Int?,
    @Json(name = "COLOR") val color: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTColor>?
)