package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.mainMenu

import android.app.Activity
import androidx.navigation.findNavController
import com.cabinInformationTechnologies.cabin.R

class CabinCustomerProfileOptionsMainMenuRouter(var activity: Activity?) :
    com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.mainMenu.CabinCustomerProfileOptionsMainMenuContracts.Router {

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