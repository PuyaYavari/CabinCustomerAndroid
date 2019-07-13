package ist.cabin.cabinCustomerLogin

import android.app.Activity

class CabinCustomerLoginRouter(var activity: Activity?) : CabinCustomerLoginContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}