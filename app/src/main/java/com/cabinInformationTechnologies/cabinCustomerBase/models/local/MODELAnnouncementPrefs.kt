package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context

class MODELAnnouncementPrefs: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    var email: Boolean? = null
    var sms: Boolean? = null
    var phone: Boolean? = null

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIAnnouncementPref
            jsonData.prefs.forEach {
                when {
                    it?.id == com.cabinInformationTechnologies.cabinCustomerBase.baseAbstracts.Announcements.EMAIL -> email = it.state
                    it?.id == com.cabinInformationTechnologies.cabinCustomerBase.baseAbstracts.Announcements.SMS -> sms = it.state
                    it?.id == com.cabinInformationTechnologies.cabinCustomerBase.baseAbstracts.Announcements.PHONE -> phone = it.state
                    else -> com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                        context,
                        this::class.java.name,
                        "Unknown id, ${it?.id}",
                        null)
                }
            }
            true
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                context,
                this::class.java.name,
                "A problem occurred while mapping AnnouncementPref!",
                exception
            )
            false
        }
    }
}