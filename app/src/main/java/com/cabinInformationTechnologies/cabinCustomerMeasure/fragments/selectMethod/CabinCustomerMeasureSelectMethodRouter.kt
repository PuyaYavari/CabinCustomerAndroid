package com.cabinInformationTechnologies.cabinCustomerMeasure.fragments.selectMethod

import android.app.Activity
import androidx.navigation.findNavController
import com.cabinInformationTechnologies.cabin.R

class CabinCustomerMeasureSelectMethodRouter(var activity: Activity?) :
    com.cabinInformationTechnologies.cabinCustomerMeasure.fragments.selectMethod.CabinCustomerMeasureSelectMethodContracts.Router {

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