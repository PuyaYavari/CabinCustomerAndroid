package ist.cabin.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JSONSex (@Json(name = "ID") val id: Int,
                    @Json(name = "NAME") val name: String)