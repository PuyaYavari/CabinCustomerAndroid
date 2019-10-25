package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JSONFilter(@Json(name = "CATEGORY") val filterCategories: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONFilterCategory?>?,
                      @Json(name = "GENDER") val sexes: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONFilterSex?>?,
                      @Json(name = "SELLER") val seller: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONFilterSeller?>?,
                      @Json(name = "COLOR") val colors: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONFilterColor?>?,
                      @Json(name = "SIZE") val filterSizes: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONFilterSizeNameGroup?>?,
                      @Json(name = "PRICE") val filterPrices: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONFilterPrice?>?)