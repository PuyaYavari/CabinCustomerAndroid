package ist.cabin.cabinCustomerCamera

import android.app.Activity

class CabinCustomerCameraRouter(var activity: Activity?) : CabinCustomerCameraContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //endregion
}