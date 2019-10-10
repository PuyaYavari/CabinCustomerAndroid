package ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions

import android.app.Activity
import androidx.navigation.findNavController
import ist.cabin.cabinCustomerBase.models.local.MODELAddress
import ist.cabin.cabincustomer.R

class CabinCustomerAddressOptionsRouter(var activity: Activity?) : CabinCustomerAddressOptionsContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToAddDeliveryAddressPage(address: MODELAddress?) {
        val action = CabinCustomerAddressOptionsFragmentDirections
            .actionAddressOptionsToDeliveryAddress(address)
        activity!!.findNavController(R.id.profile_options_nav_host_fragment).navigate(action)
    }

    override fun moveToAddInvoiceAddressPage(address: MODELAddress?) {
        val action = CabinCustomerAddressOptionsFragmentDirections
            .actionAddressOptionsToInvoiceAddress(address)
        activity!!.findNavController(R.id.profile_options_nav_host_fragment).navigate(action)
    }

    //endregion
}