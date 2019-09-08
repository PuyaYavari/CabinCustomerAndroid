package ist.cabin.cabinCustomerMeasure.fragments.autoTutorial

import android.app.Activity

class CabinCustomerMeasureAutoTutorialRouter(var activity: Activity?) :
    CabinCustomerMeasureAutoTutorialContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun toNextPage() {
        //TODO: Move to next page
    }

    //endregion
}