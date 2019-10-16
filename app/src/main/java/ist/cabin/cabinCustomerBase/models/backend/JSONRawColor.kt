package ist.cabin.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JSONRawColor (@Json(name = "ID") val id: Int,
                         @Json(name = "NAME") val name: String?,
                         @Json(name = "HEX_CODE") val hexCode: String,
                         @Json(name = "RGB_CODE") val rgbCode: String?)//FIXME: BETTER RGB CODE FROM BACKEND