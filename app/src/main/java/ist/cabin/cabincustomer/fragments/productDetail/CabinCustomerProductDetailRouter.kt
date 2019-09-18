package ist.cabin.cabincustomer.fragments.productDetail

import android.app.Activity

class CabinCustomerProductDetailRouter(var activity: Activity?) :
    CabinCustomerProductDetailContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}