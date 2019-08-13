package ist.cabin.cabincustomer.fragments.favorites

import android.app.Activity

class CabinCustomerFavoritesRouter(var activity: Activity?) :
    CabinCustomerFavoritesContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToOrdersPage() {

    }

    override fun moveToHomePage() {

    }

    override fun moveToCartPage() {

    }

    override fun moveToDiscoverPage() {

    }

    //endregion
}