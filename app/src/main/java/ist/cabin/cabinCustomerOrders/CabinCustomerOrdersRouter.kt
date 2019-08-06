package ist.cabin.cabinCustomerOrders

import android.app.Activity

class CabinCustomerOrdersRouter(var activity: Activity?) : CabinCustomerOrdersContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}