package ist.cabin.cabinCustomerExtraditions.fragments.extraditionDetail

import android.app.Activity

class CabinCustomerExtraditionDetailRouter(var activity: Activity?) :
    CabinCustomerExtraditionDetailContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}