package ist.cabin.cabinCustomerBase.models.backend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class REQUESTAPIAnnouncementPrefs(@Json(name = "ANNOUNCEMENTPREFERENCE") val prefs: List<REQUESTAnnouncemetPref>)