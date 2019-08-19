package ist.cabin.cabincustomer.fragments.orders.fragments.sent

import android.app.Activity
import androidx.navigation.findNavController
import ist.cabin.cabincustomer.R
import ist.cabin.cabincustomer.fragments.orders.CabinCustomerOrdersFragmentDirections
import ist.cabin.cabincustomer.fragments.orders.PagesIDs

class CabinCustomerOrdersSentFragmentRouter(var activity: Activity?) :
    CabinCustomerOrdersSentFragmentContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToDetailsPage() {
        val pageTypeID = PagesIDs.SENT_PAGE
        val action = CabinCustomerOrdersFragmentDirections.actionOrdersToOrdersDetail(pageTypeID)
        activity!!.findNavController(R.id.nav_host_fragment).navigate(action)
    }

    //endregion
}