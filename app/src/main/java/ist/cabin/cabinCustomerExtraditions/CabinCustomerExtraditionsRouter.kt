package ist.cabin.cabinCustomerExtraditions

import android.app.Activity

class CabinCustomerExtraditionsRouter(var activity: Activity?) :
    CabinCustomerExtraditionsContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}