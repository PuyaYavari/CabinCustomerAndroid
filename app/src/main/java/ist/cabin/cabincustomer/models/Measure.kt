package ist.cabin.cabincustomer.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Measure (@Json(name = "ID") var id: Int,
                    @Json(name = "AD") var name: String?,
                    @Json(name = "DEGER") var value: Double?)