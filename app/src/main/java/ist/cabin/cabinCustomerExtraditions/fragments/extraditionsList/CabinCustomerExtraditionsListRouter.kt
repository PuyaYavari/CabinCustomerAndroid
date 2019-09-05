package ist.cabin.cabinCustomerExtraditions.fragments.extraditionsList

import android.app.Activity
import androidx.navigation.findNavController
import ist.cabin.cabincustomer.R

class CabinCustomerExtraditionsListRouter(var activity: Activity?) :
    CabinCustomerExtraditionsListContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToAddExtraditionPage() {
        val action = CabinCustomerExtraditionsListFragmentDirections.actionExtraditionsListToAddExtraditionCurrentItemsList()
        activity!!.findNavController(R.id.extraditions_nav_host_fragment).navigate(action)
    }

    override fun moveToExtraditionDetail() {
        val action = CabinCustomerExtraditionsListFragmentDirections.actionExtraditionsListToExtraditionDetail() //TODO: SEND DATA AND SETUP PROPER PAGE
        activity!!.findNavController(R.id.extraditions_nav_host_fragment).navigate(action)
    }

    override fun moveToExtraditionDetailAccepted() {
        val action = CabinCustomerExtraditionsListFragmentDirections.actionExtraditionsListToExtraditionDetailAccepted() //TODO: SEND DATA AND SETUP PROPER PAGE
        activity!!.findNavController(R.id.extraditions_nav_host_fragment).navigate(action)
    }

    override fun moveToExtraditionDetailDenied() {
        val action = CabinCustomerExtraditionsListFragmentDirections.actionExtraditionsListToExtraditionDetailDenied() //TODO: SEND DATA AND SETUP PROPER PAGE
        activity!!.findNavController(R.id.extraditions_nav_host_fragment).navigate(action)
    }

    //endregion
}