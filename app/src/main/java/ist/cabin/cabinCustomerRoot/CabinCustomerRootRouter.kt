package ist.cabin.cabinCustomerRoot

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerLogin.CabinCustomerLoginActivity
import ist.cabin.cabinCustomerRegister.CabinCustomerRegisterActivity

class CabinCustomerRootRouter(var activity: Activity?) : CabinCustomerRootContracts.Router {
    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToLoginPage(){
        val customerLoginPage: BaseContracts.View = CabinCustomerLoginActivity()
        val intent = Intent(activity, customerLoginPage::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(activity!!.applicationContext, intent, Bundle.EMPTY)
    }

    override fun moveToRegisterPage() {
        val customerRegisterPage: BaseContracts.View = CabinCustomerRegisterActivity()
        val intent = Intent(activity, customerRegisterPage::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(activity!!.applicationContext, intent, Bundle.EMPTY)
    }

    override fun moveToInfoPage() {
        // TODO: Info button function
        Log.d("INFO BUTTON", "Pressed")
    }

    //endregion
}