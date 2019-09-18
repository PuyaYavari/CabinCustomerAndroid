package ist.cabin.cabincustomer.fragments.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerRegistration.CabinCustomerRegistrationActivity

class CabinCustomerHomeRouter(var activity: Activity?) :
    CabinCustomerHomeContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToRegistration() {
        val customerLoginActivity: BaseContracts.View = CabinCustomerRegistrationActivity()
        val intent = Intent(activity, customerLoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        ContextCompat.startActivity(activity!!.applicationContext, intent, Bundle.EMPTY)
    }

    //endregion
}