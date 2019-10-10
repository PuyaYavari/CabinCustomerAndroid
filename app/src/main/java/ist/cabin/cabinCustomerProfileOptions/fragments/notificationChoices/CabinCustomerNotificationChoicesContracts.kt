package ist.cabin.cabinCustomerProfileOptions.fragments.notificationChoices

import android.content.Context
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerBase.models.local.MODELAnnouncementPrefs

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
        fun reciveInitialData(context: Context)
    }

    interface Interactor : BaseContracts.Interactor {
        fun recieveInitialData(context: Context)
        fun sendPrefs(context: Context, prefs: MODELAnnouncementPrefs)
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        fun setPrefs(prefs: MODELAnnouncementPrefs)
        fun error(message: String?)
    }

    interface Router : BaseContracts.Router

}