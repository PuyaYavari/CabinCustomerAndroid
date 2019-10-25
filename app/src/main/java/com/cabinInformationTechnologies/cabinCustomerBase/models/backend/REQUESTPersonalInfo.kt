package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class REQUESTPersonalInfo (@Json(name = "NAME") val name: String,
                               @Json(name = "SURNAME") val surname: String,
                               @Json(name = "BIRTHDATE") val birthday: String,
                               @Json(name = "EMAIL") val email: String,
                               @Json(name = "PHONE") val phone: String,
                               @Json(name = "GENDER") val sex: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTGender>)