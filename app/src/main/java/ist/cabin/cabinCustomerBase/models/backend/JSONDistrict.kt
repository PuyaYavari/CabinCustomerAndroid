package ist.cabin.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class JSONDistrict (@Json(name = "ID") val id: Int,
                         @Json(name = "NAME") val name: String,
                         @Json(name = "CODE") val code: String?,
                         @Json(name = "CITY_CODE") val cityCode: String?)