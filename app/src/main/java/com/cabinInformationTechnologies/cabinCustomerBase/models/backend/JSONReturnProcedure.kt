package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JSONReturnProcedure (@Json(name = "DESCRIPTION") val description: String,
                                @Json(name = "STEPS") val steps: List<JSONSteps?>?,
                                @Json(name = "PAYMENT") val payment: String?,
                                @Json(name = "REMAINING_DAY") val remainingDay: String)