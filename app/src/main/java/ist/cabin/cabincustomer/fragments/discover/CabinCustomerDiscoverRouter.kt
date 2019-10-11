package ist.cabin.cabincustomer.fragments.discover

import android.app.Activity
import androidx.navigation.findNavController
import ist.cabin.cabinCustomerBase.models.local.MODELProduct
import ist.cabin.cabincustomer.R

class CabinCustomerDiscoverRouter(var activity: Activity?) :
    CabinCustomerDiscoverContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToProductDetail(product: MODELProduct) {
        val action = CabinCustomerDiscoverFragmentDirections.actionDiscoverToProductDetail(product, null)
        activity!!.findNavController(R.id.nav_host_fragment).navigate(action)
    }

    //endregion
}