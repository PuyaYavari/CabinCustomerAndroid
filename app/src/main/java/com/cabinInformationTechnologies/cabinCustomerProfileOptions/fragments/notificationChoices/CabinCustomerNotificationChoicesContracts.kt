package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.notificationChoices

import android.content.Context
import androidx.navigation.NavController
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAnnouncementPrefs

object CabinCustomerNotificationChoicesContracts {

    interface View : BaseContracts.View {
        fun enableEmail()
        fun disableEmail()
        fun enableSms()
        fun disableSms()
        fun enablePhone()
        fun disablePhone()
    }

    interface Presenter : BaseContracts.Presenter {
        fun setEmail(context: Context, emailState: Boolean)
        fun setPhone(context: Context, phoneState: Boolean)
        fun setSMS(context: Context, smsState: Boolean)
        fun receiveInitialData(context: Context, navController: NavController)
    }

    interface Interactor : BaseContracts.Interactor {
        fun receiveInitialData(context: Context, navController: NavController)
        fun sendPrefs(context: Context, prefs: MODELAnnouncementPrefs)
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        fun setPrefs(prefs: MODELAnnouncementPrefs)
    }

    interface Router : BaseContracts.Router

}