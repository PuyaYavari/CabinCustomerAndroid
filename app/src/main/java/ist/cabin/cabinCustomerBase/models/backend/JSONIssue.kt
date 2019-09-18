package ist.cabin.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JSONIssue (@Json(name = "ID") var id: Int?,
                      @Json(name = "CODE") var code: String?,
                      @Json(name = "MESSAGE") var message: String,
                      @Json(name = "URL") var url: String?)