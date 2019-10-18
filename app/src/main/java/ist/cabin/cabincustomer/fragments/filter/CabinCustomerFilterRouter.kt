package ist.cabin.cabincustomer.fragments.filter

import android.app.Activity
import androidx.navigation.findNavController
import ist.cabin.cabincustomer.R

class CabinCustomerFilterRouter(var activity: Activity?) : CabinCustomerFilterContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToFilterDetail(filterType: Int) {
        val action = CabinCustomerFilterFragmentDirections
            .actionFilterToFilterDetail(filterType)
        activity!!.findNavController(R.id.nav_host_fragment).navigate(action)
    }

    //endregion
}