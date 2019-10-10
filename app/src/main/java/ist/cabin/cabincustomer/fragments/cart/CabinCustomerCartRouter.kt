package ist.cabin.cabincustomer.fragments.cart

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerFinishTrade.CabinCustomerFinishTradeActivity

class CabinCustomerCartRouter(var activity: Activity?) :
    CabinCustomerCartContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToFinishTrade() {
        val customerFinishTradeActivity: BaseContracts.View = CabinCustomerFinishTradeActivity()
        val intent = Intent(activity, customerFinishTradeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        ContextCompat.startActivity(activity!!.applicationContext, intent, Bundle.EMPTY)
    }
    //endregion
}