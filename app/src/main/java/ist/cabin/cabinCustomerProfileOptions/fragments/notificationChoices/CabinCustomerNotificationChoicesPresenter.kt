package ist.cabin.cabinCustomerProfileOptions.fragments.notificationChoices

import android.app.Activity
import android.content.Context
import android.os.Bundle
import ist.cabin.cabinCustomerBase.models.local.MODELAnnouncementPrefs

class CabinCustomerNotificationChoicesPresenter(var view: CabinCustomerNotificationChoicesContracts.View?) :
    CabinCustomerNotificationChoicesContracts.Presenter, CabinCustomerNotificationChoicesContracts.InteractorOutput {

    var interactor: CabinCustomerNotificationChoicesContracts.Interactor? =
        CabinCustomerNotificationChoicesInteractor(this)
    var router: CabinCustomerNotificationChoicesContracts.Router? = null

    private val prefs: MODELAnnouncementPrefs = MODELAnnouncementPrefs()

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerNotificationChoicesRouter(activity)

        bundle?.let {
            //you can delete this if there's no need to get extras from the intent
        }

        prefs.email = false
        prefs.phone = false
        prefs.sms = false
    }

    override fun onDestroy() {
        view = null
        interactor?.unregister()
        interactor = null
        router?.unregister()
        router = null
    }

    //endregion

    override fun setEmail(context: Context, emailState: Boolean) {
        this.prefs.email = emailState
        interactor?.sendPrefs(context, prefs)
    }

    override fun setPhone(context: Context, phoneState: Boolean) {
        this.prefs.phone = phoneState
        interactor?.sendPrefs(context, prefs)
    }

    override fun setSMS(context: Context, smsState: Boolean) {
        this.prefs.sms = smsState
        interactor?.sendPrefs(context, prefs)
    }

    override fun reciveInitialData(context: Context) {
        interactor?.recieveInitialData(context)
    }

    //endregion

    //region InteractorOutput

    override fun setPrefs(prefs: MODELAnnouncementPrefs) {

        val emailState = prefs.email
        if (emailState != null) {
            this.prefs.email = emailState
            if (emailState) view?.enableEmail()
            else view?.disableEmail()
        }

        val smsState = prefs.sms
        if (smsState != null) {
            this.prefs.sms = smsState
            if (smsState) view?.enableSms()
            else view?.disableSms()
        }

        val phoneState = prefs.phone
        if (phoneState != null) {
            this.prefs.phone = phoneState
            if (phoneState) view?.enablePhone()
            else view?.disablePhone()
        }

    }

    override fun error(message: String?) {
        //TODO
    }
    //endregion
}