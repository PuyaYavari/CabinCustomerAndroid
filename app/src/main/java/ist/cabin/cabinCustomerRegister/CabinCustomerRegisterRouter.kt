package ist.cabin.cabinCustomerRegister

import android.app.Activity

class CabinCustomerRegisterRouter(var activity: Activity?) : CabinCustomerRegisterContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}