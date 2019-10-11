package ist.cabin.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class JSONSeller(@Json(name = "ID") val id: Int,
                      @Json(name = "NAME") val name: String,
                      @Json(name = "PRODUCT") val products: List<JSONProduct?>,
                      @Json(name = "SELLERSHIPPINGPRICE") val shippingPrice: Int?,
                      @Json(name = "SELLERSUBTOTAL") val subtotal: Int?,
                      @Json(name = "SELLERTOTAL") val total: Int)