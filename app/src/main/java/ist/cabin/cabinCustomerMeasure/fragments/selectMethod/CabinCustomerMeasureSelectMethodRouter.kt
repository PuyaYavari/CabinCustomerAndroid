package ist.cabin.cabinCustomerMeasure.fragments.selectMethod

import android.app.Activity
import androidx.navigation.findNavController
import ist.cabin.cabincustomer.R

class CabinCustomerMeasureSelectMethodRouter(var activity: Activity?) :
    CabinCustomerMeasureSelectMethodContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToAutoMeasureTurorial() {
        activity!!.findNavController(R.id.customer_measure_navhost)
            .navigate(CabinCustomerMeasureSelectMethodFragmentDirections.actionMeasureSelectMethodToMeasureAutoTutorial())
    }
    //endregion
}