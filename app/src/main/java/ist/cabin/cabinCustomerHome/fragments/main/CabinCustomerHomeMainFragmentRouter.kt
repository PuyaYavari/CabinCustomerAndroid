package ist.cabin.cabinCustomerHome.fragments.main

import android.app.Activity

class CabinCustomerHomeMainFragmentRouter(var activity: Activity?) : CabinCustomerHomeMainFragmentContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}