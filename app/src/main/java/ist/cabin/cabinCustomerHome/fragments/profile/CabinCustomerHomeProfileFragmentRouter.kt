package ist.cabin.cabinCustomerHome.fragments.profile

import android.app.Activity

class CabinCustomerHomeProfileFragmentRouter(var activity: Activity?) :
    CabinCustomerHomeProfileFragmentContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}