package ist.cabin.cabinCustomerHome

import android.app.Activity

class CabinCustomerHomeRouter(var activity: Activity?) : CabinCustomerHomeContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}