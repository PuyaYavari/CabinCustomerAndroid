package ist.cabin.cabincustomer.fragments.home

import android.app.Activity

class CabinCustomerHomeRouter(var activity: Activity?) :
    CabinCustomerHomeContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToOrdersPage() {

    }

    override fun moveToFavoritesPage() {

    }


    override fun moveToDiscoverPage() {

    }

    //endregion
}