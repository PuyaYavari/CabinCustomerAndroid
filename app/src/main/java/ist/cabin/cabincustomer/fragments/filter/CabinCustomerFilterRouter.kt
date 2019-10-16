package ist.cabin.cabincustomer.fragments.filter

import android.app.Activity

class CabinCustomerFilterRouter(var activity: Activity?) : CabinCustomerFilterContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}