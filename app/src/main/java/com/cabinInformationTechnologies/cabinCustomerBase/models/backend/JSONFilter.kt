package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JSONFilter(@Json(name = "CATEGORY") val filterCategories: List<JSONFilterCategory?>?,
                      @Json(name = "GENDER") val sexes: List<JSONFilterSex?>?,
                      @Json(name = "SELLER") val seller: List<JSONFilterSeller?>?,
                      @Json(name = "COLOR") val colors: List<JSONFilterColor?>?,
                      @Json(name = "SIZETYPE") val filterSizes: List<JSONFilterSizeNameGroup?>?,
                      @Json(name = "PRICE") val filterPrices: List<JSONFilterPrice?>?,
                      @Json(name = "PRODUCTCOUNT") val amount: Int?)