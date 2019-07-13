package ist.cabin.cabincustomer

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import ist.cabin.cabinCustomerRoot.CabinCustomerRootActivity

class MainRouter(var activity : Activity?) : MainContracts.Router {
    override fun unregister() {
        activity = null
    }

    override fun moveToRootPage(){
        val customerRootPage = CabinCustomerRootActivity()
        val intent = Intent(activity, customerRootPage::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(activity!!.applicationContext ,intent, Bundle.EMPTY)
    }
}