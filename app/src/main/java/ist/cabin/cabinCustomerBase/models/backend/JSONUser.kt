package ist.cabin.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JSONUser (@Json(name = "ID") var id: Int,
                     @Json(name = "NAME") var firstname: String?,
                     @Json(name = "SURNAME") var lastname: String?,
                     @Json(name = "SESSION") var session: String?,
                     @Json(name = "MEASURES") var JSONMeasures: List<JSONMeasure>?)