package ist.cabin.cabinCustomerProfileOptions.fragments.mainMenu

import android.app.Activity

class CabinCustomerProfileOptionsMainMenuRouter(var activity: Activity?) :
    CabinCustomerProfileOptionsMainMenuContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}