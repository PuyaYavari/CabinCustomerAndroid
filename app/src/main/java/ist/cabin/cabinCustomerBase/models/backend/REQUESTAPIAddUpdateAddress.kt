package ist.cabin.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class REQUESTAPIAddUpdateAddress(@Json(name = "ADDRESS") val addresses: List<REQUESTAddUpdateAddress?>)