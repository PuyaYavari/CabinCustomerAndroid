package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class REQUESTAPIDistrict (@Json(name = "CITY") val city: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTDistrict>)