package ist.cabin.cabinCustomerBase.models.local

import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.baseAbstracts.Announcements
import ist.cabin.cabinCustomerBase.models.backend.APIAnnouncementPref

class MODELAnnouncementPrefs: LocalDataModel {
    var email: Boolean? = null
    var sms: Boolean? = null
    var phone: Boolean? = null

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as APIAnnouncementPref
            jsonData.prefs.forEach {
                when {
                    it?.id == Announcements.EMAIL -> email = it.state
                    it?.id == Announcements.SMS -> sms = it.state
                    it?.id == Announcements.PHONE -> phone = it.state
                    else -> Logger.warn(this::class.java.name, "Unknown id, ${it?.id}", null)
                }
            }
            true
        } catch (exception: Exception) {
            Logger.error(this::class.java.name,
                "A problem occurred while mapping AnnouncementPref!",
                exception
            )
            false
        }
    }
}