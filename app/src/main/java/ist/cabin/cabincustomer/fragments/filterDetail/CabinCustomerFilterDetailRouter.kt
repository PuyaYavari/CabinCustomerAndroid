package ist.cabin.cabincustomer.fragments.filterDetail

import android.app.Activity

class CabinCustomerFilterDetailRouter(var activity: Activity?) :
    CabinCustomerFilterDetailContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}