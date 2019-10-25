package com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtraditionCurrentItemsList

import android.app.Activity
import androidx.navigation.findNavController
import com.cabinInformationTechnologies.cabin.R

class CabinCustomerAddExtraditionCurrentItemsListRouter(var activity: Activity?) :
    com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtraditionCurrentItemsList.CabinCustomerAddExtraditionCurrentItemsListContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveForward() {
        val action =
            CabinCustomerAddExtraditionCurrentItemsListFragmentDirections.actionAddExtraditionCurrentItemsListToAddExtradition()
        activity!!.findNavController(R.id.extraditions_nav_host_fragment).navigate(action)
    }

    //endregion
}