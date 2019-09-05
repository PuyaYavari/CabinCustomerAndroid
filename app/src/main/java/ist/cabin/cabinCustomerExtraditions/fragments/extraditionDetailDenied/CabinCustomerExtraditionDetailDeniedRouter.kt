package ist.cabin.cabinCustomerExtraditions.fragments.extraditionDetailDenied

import android.app.Activity

class CabinCustomerExtraditionDetailDeniedRouter(var activity: Activity?) :
    CabinCustomerExtraditionDetailDeniedContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}