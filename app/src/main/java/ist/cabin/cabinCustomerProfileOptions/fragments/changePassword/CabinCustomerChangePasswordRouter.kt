package ist.cabin.cabinCustomerProfileOptions.fragments.changePassword

import android.app.Activity

class CabinCustomerChangePasswordRouter(var activity: Activity?) : CabinCustomerChangePasswordContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //endregion
}