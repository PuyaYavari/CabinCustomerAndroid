package ist.cabin.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class REQUESTAPIUpdatePassword(@Json(name = "USER") val passwords: List<REQUESTUpdatePassword>)