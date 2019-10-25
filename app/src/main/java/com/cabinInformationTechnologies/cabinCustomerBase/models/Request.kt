package com.cabinInformationTechnologies.cabinCustomerBase.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Request<T> (@Json(name = "USER") var UserAuth: com.cabinInformationTechnologies.cabinCustomerBase.models.UserAuth?,
                  @Json(name = "PAGING") var paging: com.cabinInformationTechnologies.cabinCustomerBase.models.Paging?,
                  @Json(name = "DATA") var data: T?)