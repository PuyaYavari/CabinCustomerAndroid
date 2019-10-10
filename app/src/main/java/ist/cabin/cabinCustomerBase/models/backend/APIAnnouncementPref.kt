package ist.cabin.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class APIAnnouncementPref(@Json(name = "ANNOUNCEMENTPREFERENCE") val prefs: List<JSONAnnouncementPref?>)