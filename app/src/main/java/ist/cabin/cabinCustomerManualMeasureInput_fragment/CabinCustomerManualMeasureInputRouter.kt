package ist.cabin.cabinCustomerManualMeasureInput_fragment

import android.app.Activity

class CabinCustomerManualMeasureInputRouter(var activity: Activity?) :
    CabinCustomerManualMeasureInputContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}