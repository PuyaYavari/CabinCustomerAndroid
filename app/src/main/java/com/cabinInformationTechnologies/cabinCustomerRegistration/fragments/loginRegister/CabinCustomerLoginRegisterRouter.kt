package com.cabinInformationTechnologies.cabinCustomerRegistration.fragments.loginRegister

import android.app.Activity
import androidx.navigation.findNavController
import com.cabinInformationTechnologies.cabin.R

class CabinCustomerLoginRegisterRouter(var activity: Activity?) :
    CabinCustomerLoginRegisterContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToForgotPassword() {
        val action = CabinCustomerLoginRegisterFragmentDirections.actionLoginToForgotPassword()
        activity!!.findNavController(R.id.customer_registration_navhost).navigate(action)
    }

    //endregion
}