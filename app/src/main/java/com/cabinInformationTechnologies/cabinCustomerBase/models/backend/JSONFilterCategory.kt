package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JSONFilterCategory (@Json(name = "ID") val id: Int,
                               @Json(name = "NAME") val name: String,
                               @Json(name = "AMOUNT") val amount: Int?,
                               @Json(name = "ISSELECTED") val isSelected: Boolean?,
                               @Json(name = "CATEGORY") val filterCategories: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONFilterCategory?>?)