package ist.cabin.cabinCustomerHome

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerCart.CabinCustomerCartActivity
import ist.cabin.cabinCustomerDiscover.CabinCustomerDiscoverActivity
import ist.cabin.cabinCustomerFavorites.CabinCustomerFavoritesActivity
import ist.cabin.cabinCustomerOrders.CabinCustomerOrdersActivity

class CabinCustomerHomeRouter(var activity: Activity?) : CabinCustomerHomeContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToOrdersPage() {
        val ordersPage: BaseContracts.View = CabinCustomerOrdersActivity()
        val intent = Intent(activity, ordersPage::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        ContextCompat.startActivity(activity!!.applicationContext, intent, Bundle.EMPTY)
    }

    override fun moveToFavoritesPage() {
        val favoritesPage: BaseContracts.View = CabinCustomerFavoritesActivity()
        val intent = Intent(activity, favoritesPage::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        ContextCompat.startActivity(activity!!.applicationContext, intent, Bundle.EMPTY)
    }

    override fun moveToCartPage() {
        val cartPage: BaseContracts.View = CabinCustomerCartActivity()
        val intent = Intent(activity, cartPage::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        ContextCompat.startActivity(activity!!.applicationContext, intent, Bundle.EMPTY)
    }

    override fun moveToDiscoverPage() {
        val discoverPage: BaseContracts.View = CabinCustomerDiscoverActivity()
        val intent = Intent(activity, discoverPage::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        ContextCompat.startActivity(activity!!.applicationContext, intent, Bundle.EMPTY)
    }

    //endregion
}