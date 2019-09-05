package ist.cabin.cabinCustomerExtraditions.fragments.addExtraditionCurrentItemsList

import android.app.Activity
import androidx.navigation.findNavController
import ist.cabin.cabincustomer.R

class CabinCustomerAddExtraditionCurrentItemsListRouter(var activity: Activity?) :
    CabinCustomerAddExtraditionCurrentItemsListContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveForward() {
        val action = CabinCustomerAddExtraditionCurrentItemsListFragmentDirections.actionAddExtraditionCurrentItemsListToAddExtradition()
        activity!!.findNavController(R.id.extraditions_nav_host_fragment).navigate(action)
    }

    //endregion
}