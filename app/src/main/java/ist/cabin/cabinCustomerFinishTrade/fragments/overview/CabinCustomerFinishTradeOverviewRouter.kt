package ist.cabin.cabinCustomerFinishTrade.fragments.overview

import android.app.Activity

class CabinCustomerFinishTradeOverviewRouter(var activity: Activity?) :
    CabinCustomerFinishTradeOverviewContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}