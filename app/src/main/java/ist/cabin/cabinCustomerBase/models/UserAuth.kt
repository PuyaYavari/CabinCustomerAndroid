package ist.cabin.cabinCustomerBase.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class UserAuth (@Json(name = "SESSION") var session: String,
                @Json(name = "ID") var id: Int)