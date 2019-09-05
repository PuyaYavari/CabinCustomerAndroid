package ist.cabin.cabinCustomerFinishTrade

import android.app.Activity

class CabinCustomerFinishTradeRouter(var activity: Activity?) :
    CabinCustomerFinishTradeContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}