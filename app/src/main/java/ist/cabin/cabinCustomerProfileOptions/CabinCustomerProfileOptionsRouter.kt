package ist.cabin.cabinCustomerProfileOptions

import android.app.Activity

class CabinCustomerProfileOptionsRouter(var activity: Activity?) : CabinCustomerProfileOptionsContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //endregion
}