package ist.cabin.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class JSONCart(@Json(name = "SELLER") val seller: List<JSONSeller?>,
                    @Json(name = "SHIPPINGPRICE") val shippingPrice: Double?,
                    @Json(name = "SUBTOTAL") val subtotal: Double?,
                    @Json(name = "TOTAL") val total: Double)