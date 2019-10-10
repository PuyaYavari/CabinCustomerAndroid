package ist.cabin.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class REQUESTAPIDistrict (@Json(name = "CITY") val city: List<REQUESTDistrict>)