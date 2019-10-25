package com.cabinInformationTechnologies.cabinCustomerBase.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Paging(@Json(name = "PAGE_NUMBER") var pageNumber: Int,
             @Json(name = "PAGE_SIZE") var pageSize: Int)