package ist.cabin.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class REQUESTAPIPersonalInfo (@Json(name = "USER") val personalInfo: List<REQUESTPersonalInfo>)