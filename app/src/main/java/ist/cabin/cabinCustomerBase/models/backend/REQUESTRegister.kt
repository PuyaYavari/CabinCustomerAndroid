package ist.cabin.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class REQUESTRegister(
    @Json(name = "ID") val id: Int,
    @Json(name = "USERNAME") val username: String,
    @Json(name = "PASSWORD") val password: String,
    @Json(name = "WANTSADSEMAIL") val emailPermitted: Boolean,
    @Json(name = "GENDER") val gender: REQUESTGender
)