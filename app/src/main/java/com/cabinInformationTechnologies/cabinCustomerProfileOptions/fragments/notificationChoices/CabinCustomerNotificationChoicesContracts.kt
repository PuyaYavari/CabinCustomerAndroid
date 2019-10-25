package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.notificationChoices

import android.content.Context

object CabinCustomerNotificationChoicesContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        fun enableEmail()
        fun disableEmail()
        fun enableSms()
        fun disableSms()
        fun enablePhone()
        fun disablePhone()
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        fun setEmail(context: Context, emailState: Boolean)
        fun setPhone(context: Context, phoneState: Boolean)
        fun setSMS(context: Context, smsState: Boolean)
        fun reciveInitialData(context: Context)
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {
        fun recieveInitialData(context: Context)
        fun sendPrefs(context: Context, prefs: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAnnouncementPrefs)
    }

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput {
        fun setPrefs(prefs: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAnnouncementPrefs)
        fun error(message: String?)
    }

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router

}