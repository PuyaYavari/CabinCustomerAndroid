package com.cabinInformationTechnologies.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class REQUESTFilter (@Json(name = "CATEGORY") val category: List<REQUESTWITHID?>?,
                          @Json(name = "GENDER") val sexes: List<REQUESTWITHID?>?,
                          @Json(name = "SELLER") val sellers: List<REQUESTWITHID?>?,
                          @Json(name = "COLOR") val colors: List<REQUESTWITHID?>?,
                          @Json(name = "SIZE") val sizes: List<REQUESTWITHID?>?,
                          @Json(name = "PRICE") val prices: List<REQUESTWITHID?>?)