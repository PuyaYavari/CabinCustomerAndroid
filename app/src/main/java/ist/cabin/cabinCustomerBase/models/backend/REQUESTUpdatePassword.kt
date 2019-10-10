package ist.cabin.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class REQUESTUpdatePassword(@Json(name = "PASSWORD_OLD") val oldPassword: String,
                                 @Json(name = "PASSWORD_NEW") val newPassword: String)