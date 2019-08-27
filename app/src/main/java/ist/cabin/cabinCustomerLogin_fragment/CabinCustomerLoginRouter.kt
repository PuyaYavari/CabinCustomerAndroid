package ist.cabin.cabinCustomerLogin_fragment

import android.app.Activity

class CabinCustomerLoginRouter(var activity: Activity?) :
    CabinCustomerLoginContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToHomePage() {

    }

    override fun moveToForgetpasswordPage() {

    }

    //endregion
}