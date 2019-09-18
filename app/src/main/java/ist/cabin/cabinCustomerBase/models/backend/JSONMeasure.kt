package ist.cabin.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JSONMeasure (@Json(name = "ID") var id: Int,
                        @Json(name = "NAME") var name: String?,
                        @Json(name = "VALUE") var value: Double?)