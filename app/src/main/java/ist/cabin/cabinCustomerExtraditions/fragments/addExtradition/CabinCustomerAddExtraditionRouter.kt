package ist.cabin.cabinCustomerExtraditions.fragments.addExtradition

import android.app.Activity
import androidx.navigation.findNavController
import ist.cabin.cabincustomer.R

class CabinCustomerAddExtraditionRouter(var activity: Activity?) :
    CabinCustomerAddExtraditionContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToCongratulationsPage() {
        val action = CabinCustomerAddExtraditionFragmentDirections.actionAddExtraditionToExtraditionsCongratulations()
        activity!!.findNavController(R.id.extraditions_nav_host_fragment).navigate(action)
    }

    //endregion
}