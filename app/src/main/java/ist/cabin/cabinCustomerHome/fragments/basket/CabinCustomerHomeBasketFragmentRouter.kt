package ist.cabin.cabinCustomerHome.fragments.basket

import android.app.Activity

class CabinCustomerHomeBasketFragmentRouter(var activity: Activity?) : CabinCustomerHomeBasketFragmentContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}