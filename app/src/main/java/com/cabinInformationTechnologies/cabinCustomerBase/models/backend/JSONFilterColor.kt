package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JSONFilterColor (@Json(name = "ID") val id: Int,
                            @Json(name = "NAME") val name: String?,
                            @Json(name = "HEX_CODE") val hexCode: String,
                            @Json(name = "RGB_CODE") val rgbCode: String?,
                            @Json(name = "AMOUNT") val amount: Int,
                            @Json(name = "ISSELECTED") val isSelected: Boolean)