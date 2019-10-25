package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class JSONAddress (@Json(name = "ID") val id: Int,
                        @Json(name = "BILLING") val isBilling: Boolean,
                        @Json(name = "NAME") val name: String,
                        @Json(name = "SURNAME") val surname: String,
                        @Json(name = "PHONE") val phone: String,
                        @Json(name = "CITY") val province: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONCity>,
                        @Json(name = "DISTRICT") val district: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONDistrict>,
                        @Json(name = "ADDRESS") val address: String,
                        @Json(name = "TITLE") val title: String,
                        @Json(name = "INDIVIDUAL") val isIndividual: Boolean,
                        @Json(name = "COMPANY_NAME") val companyName: String?,
                        @Json(name = "TAX_NUMBER") val taxNumber: String?,
                        @Json(name = "TAX_AUTHORITY") val taxAuthority: String?)