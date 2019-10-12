package ist.cabin.cabincustomer

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerExtraditions.CabinCustomerExtraditionsActivity
import ist.cabin.cabinCustomerMeasure.CabinCustomerMeasureActivity
import ist.cabin.cabinCustomerProfileOptions.CabinCustomerProfileOptionsActivity
import ist.cabin.cabinCustomerRegistration.CabinCustomerRegistrationActivity

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

    override fun moveToMeasure() {
        val customerMeasureActivity: BaseContracts.View = CabinCustomerMeasureActivity()
        val intent = Intent(activity, customerMeasureActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(activity!!.applicationContext, intent, Bundle.EMPTY)
    }

    override fun moveToExtraditions() {
        val customerExtraditionsActivity: BaseContracts.View = CabinCustomerExtraditionsActivity()
        val intent = Intent(activity, customerExtraditionsActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(activity!!.applicationContext, intent, Bundle.EMPTY)
    }

    override fun moveToRegistration() {
        val customerLoginActivity: BaseContracts.View = CabinCustomerRegistrationActivity()
        val intent = Intent(activity, customerLoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(activity!!.applicationContext, intent, Bundle.EMPTY)
    }
}