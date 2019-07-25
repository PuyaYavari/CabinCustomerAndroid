package ist.cabin.cabinCustomerEmailconfirmation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import ist.cabin.cabinCustomerRoot.CabinCustomerRootActivity

class CabinCustomerEmailconfirmationRouter(var activity: Activity?) : CabinCustomerEmailconfirmationContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToRootPage() {
        val customerRootPage = CabinCustomerRootActivity()
        val intent = Intent(activity, customerRootPage::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        ContextCompat.startActivity(activity!!.applicationContext, intent, Bundle.EMPTY)
    }

    //endregion
}