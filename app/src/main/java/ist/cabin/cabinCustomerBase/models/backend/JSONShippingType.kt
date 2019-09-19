package ist.cabin.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class JSONShippingType (@Json(name = "ID") val id: Int,
                             @Json(name = "NAME") val name: String)