package ist.cabin.cabinCustomerEmailConfirmation_fragment

import android.app.Activity

class CabinCustomerEmailconfirmationRouter(var activity: Activity?) :
    CabinCustomerEmailconfirmationContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToRootPage() {

    }

    //endregion
}