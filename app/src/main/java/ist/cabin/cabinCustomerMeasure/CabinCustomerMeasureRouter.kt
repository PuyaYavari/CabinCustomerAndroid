package ist.cabin.cabinCustomerMeasure

import android.app.Activity

class CabinCustomerMeasureRouter(var activity: Activity?) : CabinCustomerMeasureContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}