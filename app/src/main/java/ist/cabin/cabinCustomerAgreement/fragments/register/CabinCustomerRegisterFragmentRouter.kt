package ist.cabin.cabincustomer.fragments.register

import android.app.Activity

class CabinCustomerRegisterFragmentRouter(var activity: Activity?) :
    CabinCustomerRegisterFragmentContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun goForward() {

    }


    //endregion
}