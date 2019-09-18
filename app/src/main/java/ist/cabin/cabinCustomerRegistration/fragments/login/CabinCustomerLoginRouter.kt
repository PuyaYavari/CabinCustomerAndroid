package ist.cabin.cabinCustomerRegistration.fragments.login

import android.app.Activity

class CabinCustomerLoginRouter(var activity: Activity?) :
    CabinCustomerLoginContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router


    //endregion
}