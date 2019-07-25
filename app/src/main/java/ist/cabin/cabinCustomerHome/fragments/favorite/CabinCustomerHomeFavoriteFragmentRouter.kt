package ist.cabin.cabinCustomerHome.fragments.favorite

import android.app.Activity

class CabinCustomerHomeFavoriteFragmentRouter(var activity: Activity?) :
    CabinCustomerHomeFavoriteFragmentContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}