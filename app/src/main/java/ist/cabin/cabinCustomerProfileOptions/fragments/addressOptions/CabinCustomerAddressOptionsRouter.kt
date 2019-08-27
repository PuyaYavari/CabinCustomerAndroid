package ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions

import android.app.Activity
import androidx.navigation.findNavController
import ist.cabin.cabincustomer.R

class CabinCustomerAddressOptionsRouter(var activity: Activity?) : CabinCustomerAddressOptionsContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToAddDeliveryAddressPage(
        name: String?,
        surname: String?,
        phone: String?,
        province: String?,
        district: String?,
        address: String?,
        addressHeader: String?
    ) {
        val action = CabinCustomerAddressOptionsFragmentDirections
            .actionAddressOptionsToDeliveryAddress(name, surname, phone, province, district, address, addressHeader)
        activity!!.findNavController(R.id.profile_options_nav_host_fragment).navigate(action)
    }

    override fun moveToAddInvoiceAddressPage(
        name: String?,
        surname: String?,
        phone: String?,
        province: String?,
        district: String?,
        address: String?,
        addressHeader: String?,
        isCorporate: Boolean,
        corporationName: String?,
        taxNo: String?,
        taxAdministration: String?
    ) {
        val action = CabinCustomerAddressOptionsFragmentDirections
            .actionAddressOptionsToInvoiceAddress(name, surname, phone, province, district, address,
                addressHeader, isCorporate, corporationName, taxNo, taxAdministration)
        activity!!.findNavController(R.id.profile_options_nav_host_fragment).navigate(action)
    }

    //endregion
}