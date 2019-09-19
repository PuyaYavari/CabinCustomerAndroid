package ist.cabin.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class JSONImage(@Json(name = "URL") val url: String,
                     @Json(name = "PRIORITY") val priority: Boolean?)