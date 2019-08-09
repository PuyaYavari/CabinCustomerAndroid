package ist.cabin.cabinCustomerOrders.fragments.sent

import android.app.Activity

class CabinCustomerOrdersSentFragmentRouter(var activity: Activity?) : CabinCustomerOrdersSentFragmentContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}