package ist.cabin.cabinCustomerOrders.fragments.shipping

import android.app.Activity

class CabinCustomerOrdersShippingFragmentRouter(var activity: Activity?) :
    CabinCustomerOrdersShippingFragmentContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}