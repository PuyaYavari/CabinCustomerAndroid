package ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions

import android.app.Activity
import androidx.navigation.findNavController
import ist.cabin.cabincustomer.R

class CabinCustomerAddressOptionsRouter(var activity: Activity?) : CabinCustomerAddressOptionsContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToAddDeliveryAddressPage() {
        val action = CabinCustomerAddressOptionsFragmentDirections.actionAddressOptionsToDeliveryAddress()
        activity!!.findNavController(R.id.profile_options_nav_host_fragment).navigate(action)
    }

    override fun moveToAddInvoiceAddressPage() {
        val action = CabinCustomerAddressOptionsFragmentDirections.actionAddressOptionsToInvoiceAddress()
        activity!!.findNavController(R.id.profile_options_nav_host_fragment).navigate(action)
    }

    //endregion
}