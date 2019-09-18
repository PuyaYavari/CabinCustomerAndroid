package ist.cabin.cabinCustomerBase.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Request<T> (@Json(name = "USER") var UserAuth: UserAuth?,
                  @Json(name = "PAGING") var paging: Paging?,
                  @Json(name = "DATA") var data: T?)