package ist.cabin.cabincustomer.fragments.favorites

import android.app.Activity
import androidx.navigation.findNavController
import ist.cabin.cabinCustomerBase.models.local.MODELProduct
import ist.cabin.cabincustomer.R

class CabinCustomerFavoritesRouter(var activity: Activity?) :
    CabinCustomerFavoritesContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToProductDetail(product: MODELProduct) {
        val action = CabinCustomerFavoritesFragmentDirections.actionFavoritesToProductDetail(product)//TODO: SEND PRODUCT
        activity!!.findNavController(R.id.nav_host_fragment).navigate(action)
    }

    //endregion
}