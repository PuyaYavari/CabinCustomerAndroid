package ist.cabin.cabincustomer.fragments.discover

import android.app.Activity

class CabinCustomerDiscoverRouter(var activity: Activity?) :
    CabinCustomerDiscoverContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToOrdersPage() {

    }

    override fun moveToFavoritesPage() {

    }

    override fun moveToHomePage() {

    }

    override fun moveToCartPage() {

    }

    //endregion
}