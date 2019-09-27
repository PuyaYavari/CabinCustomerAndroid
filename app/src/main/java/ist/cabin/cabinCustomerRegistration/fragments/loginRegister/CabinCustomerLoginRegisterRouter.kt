package ist.cabin.cabinCustomerRegistration.fragments.loginRegister

import android.app.Activity

class CabinCustomerLoginRegisterRouter(var activity: Activity?) :
    CabinCustomerLoginRegisterContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router


    //endregion
}