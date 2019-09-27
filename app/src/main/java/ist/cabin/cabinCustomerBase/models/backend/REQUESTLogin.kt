package ist.cabin.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class REQUESTLogin (@Json(name = "USERNAME") var email: String,
                         @Json(name = "PASSWORD") var password: String)