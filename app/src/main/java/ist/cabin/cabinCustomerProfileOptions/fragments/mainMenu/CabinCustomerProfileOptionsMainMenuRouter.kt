package ist.cabin.cabinCustomerProfileOptions.fragments.mainMenu

import android.app.Activity
import androidx.navigation.findNavController
import ist.cabin.cabincustomer.R

class CabinCustomerProfileOptionsMainMenuRouter(var activity: Activity?) :
    CabinCustomerProfileOptionsMainMenuContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToPersonalDataPage() {
        activity!!.findNavController(R.id.profile_options_nav_host_fragment)
            .navigate(CabinCustomerProfileOptionsMainMenuFragmentDirections.actionMainMenuToPersonalDataOptions())
    }

    override fun moveToAddressOptionsPage() {
        activity!!.findNavController(R.id.profile_options_nav_host_fragment)
            .navigate(CabinCustomerProfileOptionsMainMenuFragmentDirections.actionMainMenuToAddressOptions())
    }

    override fun moveToChangePasswordPage() {
        activity!!.findNavController(R.id.profile_options_nav_host_fragment)
            .navigate(CabinCustomerProfileOptionsMainMenuFragmentDirections.actionMainMenuToChangePassword())
    }

    override fun moveToNotificationChoicesPage() {
        activity!!.findNavController(R.id.profile_options_nav_host_fragment)
            .navigate(CabinCustomerProfileOptionsMainMenuFragmentDirections.actionMainMenuToNotificationChoices())
    }

    //endregion
}