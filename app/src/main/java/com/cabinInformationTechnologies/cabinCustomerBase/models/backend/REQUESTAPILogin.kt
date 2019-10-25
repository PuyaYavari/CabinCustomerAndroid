package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class REQUESTAPILogin (@Json(name = "USER") var userArray: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTLogin>?)