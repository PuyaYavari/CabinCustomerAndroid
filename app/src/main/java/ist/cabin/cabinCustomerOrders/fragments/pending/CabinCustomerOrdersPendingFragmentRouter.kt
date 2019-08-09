package ist.cabin.cabinCustomerOrders.fragments.pending

import android.app.Activity

class CabinCustomerOrdersPendingFragmentRouter(var activity: Activity?) :
    CabinCustomerOrdersPendingFragmentContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}