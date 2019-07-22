package ist.cabin.cabincustomer.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse (@Json(name = "KULLANICI") var userArray: Array<User>?)
