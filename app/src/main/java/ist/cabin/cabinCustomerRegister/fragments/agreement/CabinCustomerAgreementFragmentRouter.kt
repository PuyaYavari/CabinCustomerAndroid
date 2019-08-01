package ist.cabin.cabinCustomerRegister.fragments.agreement

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import ist.cabin.cabinCustomerEmailconfirmation.CabinCustomerEmailconfirmationActivity

class CabinCustomerAgreementFragmentRouter(var activity: Activity?) : CabinCustomerAgreementFragmentContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun goForward() {
        val customerEmailConfirmationPage = CabinCustomerEmailconfirmationActivity()
        val intent = Intent(activity, customerEmailConfirmationPage::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        ContextCompat.startActivity(activity!!.applicationContext, intent, Bundle.EMPTY)
    }

    //endregion
}