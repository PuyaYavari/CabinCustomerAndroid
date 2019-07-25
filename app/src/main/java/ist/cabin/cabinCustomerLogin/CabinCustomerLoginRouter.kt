package ist.cabin.cabinCustomerLogin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerForgotpassword.CabinCustomerForgotpasswordActivity
import ist.cabin.cabinCustomerHome.CabinCustomerHomeActivity

class CabinCustomerLoginRouter(var activity: Activity?) : CabinCustomerLoginContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToHomePage() {
        val customerHomePage: BaseContracts.View = CabinCustomerHomeActivity()
        val intent = Intent(activity, customerHomePage::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        ContextCompat.startActivity(activity!!.applicationContext, intent, Bundle.EMPTY)
    }

    override fun moveToForgetpasswordPage() {
        val customerForgetpasswordPage: BaseContracts.View = CabinCustomerForgotpasswordActivity()
        val intent = Intent(activity, customerForgetpasswordPage::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        ContextCompat.startActivity(activity!!.applicationContext, intent, Bundle.EMPTY)
    }

    //endregion
}