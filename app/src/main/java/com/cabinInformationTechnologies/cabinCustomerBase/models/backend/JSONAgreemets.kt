package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JSONAgreemets (@Json(name = "PRELIMINARY_INFORMATION_FORM") val PIF: String,
                          @Json(name = "DISTANCE_SALE_AGREEMENT") val DSA: String)