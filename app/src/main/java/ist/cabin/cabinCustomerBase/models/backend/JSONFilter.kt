package ist.cabin.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JSONFilter(@Json(name = "CATEGORY") val categories: List<JSONCategory?>?,
                      @Json(name = "GENDER") val sexes: List<JSONSex?>?,
                      @Json(name = "SELLER") val seller: List<JSONSellerName?>?,
                      @Json(name = "COLOR") val colors: List<JSONRawColor?>?,
                      @Json(name = "SIZE") val sizes: List<JSONSizeNameGroup?>?,
                      @Json(name = "PRICE") val prices: List<JSONPriceInterval?>?)