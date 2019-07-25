package ist.cabin.cabincustomer.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginData (@Json(name = "KULLANICIAD") var email: String,
                      @Json(name = "SIFRE") var password: String)