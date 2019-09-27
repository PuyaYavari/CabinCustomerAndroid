package ist.cabin.cabincustomer.fragments.orders

import android.app.Activity

class CabinCustomerOrdersRouter(var activity: Activity?) :
    CabinCustomerOrdersContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //endregion
}