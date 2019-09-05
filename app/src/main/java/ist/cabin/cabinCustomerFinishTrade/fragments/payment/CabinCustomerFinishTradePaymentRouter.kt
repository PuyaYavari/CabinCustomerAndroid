package ist.cabin.cabinCustomerFinishTrade.fragments.payment

import android.app.Activity

class CabinCustomerFinishTradePaymentRouter(var activity: Activity?) :
    CabinCustomerFinishTradePaymentContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}