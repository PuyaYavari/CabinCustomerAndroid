package ist.cabin.cabinCustomerDiscover

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerCart.CabinCustomerCartActivity
import ist.cabin.cabinCustomerFavorites.CabinCustomerFavoritesActivity
import ist.cabin.cabinCustomerHome.CabinCustomerHomeActivity
import ist.cabin.cabinCustomerOrders.CabinCustomerOrdersActivity

class CabinCustomerDiscoverRouter(var activity: Activity?) : CabinCustomerDiscoverContracts.Router {

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

    override fun moveToHomePage() {
        val homePage: BaseContracts.View = CabinCustomerHomeActivity()
        val intent = Intent(activity, homePage::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        ContextCompat.startActivity(activity!!.applicationContext, intent, Bundle.EMPTY)
    }

    override fun moveToCartPage() {
        val cartPage: BaseContracts.View = CabinCustomerCartActivity()
        val intent = Intent(activity, cartPage::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        ContextCompat.startActivity(activity!!.applicationContext, intent, Bundle.EMPTY)
    }

    //endregion
}