package ist.cabin.cabinCustomerExtraditions.fragments.congratulations

import android.app.Activity

class CabinCustomerExtraditionsCongratulationsRouter(var activity: Activity?) :
    CabinCustomerExtraditionsCongratulationsContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}