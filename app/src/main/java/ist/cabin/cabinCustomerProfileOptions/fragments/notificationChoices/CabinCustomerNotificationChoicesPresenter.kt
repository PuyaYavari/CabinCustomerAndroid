package ist.cabin.cabinCustomerProfileOptions.fragments.notificationChoices

import android.app.Activity
import android.os.Bundle

class CabinCustomerNotificationChoicesPresenter(var view: CabinCustomerNotificationChoicesContracts.View?) :
    CabinCustomerNotificationChoicesContracts.Presenter, CabinCustomerNotificationChoicesContracts.InteractorOutput {

    var interactor: CabinCustomerNotificationChoicesContracts.Interactor? =
        CabinCustomerNotificationChoicesInteractor(this)
    var router: CabinCustomerNotificationChoicesContracts.Router? = null

    private var email: Boolean = false
    private var phone: Boolean = false
    private var sms: Boolean = false

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerNotificationChoicesRouter(activity)

        bundle?.let {
            //you can delete this if there's no need to get extras from the intent
        }
    }

    override fun onDestroy() {
        view = null
        interactor?.unregister()
        interactor = null
        router?.unregister()
        router = null
    }

    //endregion

    override fun setEmail(emailState: Boolean) {
        this.email = emailState //TODO: SEND TO BACKEND
    }

    override fun setPhone(phoneState: Boolean) {
        this.phone = phoneState //TODO: SEND TO BACKEND
    }

    override fun setSMS(smsState: Boolean) {
        this.sms = smsState //TODO: SEND TO BACKEND
    }

    override fun reciveInitialData() {
        interactor?.recieveInitialData() //TODO: RECEIVE AND RETURN INITIAL DATA
    }

    //endregion

    //region InteractorOutput

    //endregion
}