package ist.cabin.cabincustomer.fragments.cart

import android.app.Activity

class CabinCustomerCartRouter(var activity: Activity?) :
    CabinCustomerCartContracts.Router {

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

    override fun moveToDiscoverPage() {

    }

    //endregion
}