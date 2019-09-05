package ist.cabin.cabinCustomerExtraditions.fragments.extraditionDetailAccepted

import android.app.Activity

class CabinCustomerExtraditionDetailAcceptedRouter(var activity: Activity?) :
    CabinCustomerExtraditionDetailAcceptedContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}