package ist.cabin.cabinCustomerProfileOptions.fragment.addressOptions

import android.app.Activity

class CabinCustomerAddressOptionsRouter(var activity: Activity?) : CabinCustomerAddressOptionsContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}