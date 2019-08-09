package ist.cabin.cabincustomer.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User (@Json(name = "ID") var id: Int,
                 @Json(name = "AD") var firstname: String?,
                 @Json(name = "SOYAD") var lastname: String?,
                 @Json(name = "KULLANICIAD") var username: String?,
                 @Json(name = "OLCU") var measures: List<Measure>?)