package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions

import android.app.Activity
import androidx.navigation.findNavController
import com.cabinInformationTechnologies.cabin.R

class CabinCustomerAddressOptionsRouter(var activity: Activity?) :
    com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.CabinCustomerAddressOptionsContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToAddDeliveryAddressPage(address: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress?) {
        val action =
            CabinCustomerAddressOptionsFragmentDirections.actionAddressOptionsToDeliveryAddress(
                address
            )
        activity!!.findNavController(R.id.profile_options_nav_host_fragment).navigate(action)
    }

    override fun moveToAddInvoiceAddressPage(address: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress?) {
        val action =
            CabinCustomerAddressOptionsFragmentDirections.actionAddressOptionsToInvoiceAddress(
                address
            )
        activity!!.findNavController(R.id.profile_options_nav_host_fragment).navigate(action)
    }

    //endregion
}