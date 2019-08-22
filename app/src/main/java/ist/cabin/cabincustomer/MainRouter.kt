package ist.cabin.cabincustomer

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerProfileOptions.CabinCustomerProfileOptionsActivity

class MainRouter(var activity : Activity?) : MainContracts.Router {
    override fun unregister() {
        activity = null
    }

    override fun moveToProfileOptions() {
        val customerProfileOptionsActivity: BaseContracts.View = CabinCustomerProfileOptionsActivity()
        val intent = Intent(activity, customerProfileOptionsActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(activity!!.applicationContext, intent, Bundle.EMPTY)
    }
}