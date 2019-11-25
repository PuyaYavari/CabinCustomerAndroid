package com.cabinInformationTechnologies.cabinCustomerRegistration.fragments.forgotPassword

import android.app.Activity
import androidx.navigation.findNavController
import com.cabinInformationTechnologies.cabin.R

class CabinCustomerForgotPasswordRouter(var activity: Activity?) :
    CabinCustomerForgotPasswordContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToResetPassword() {
        val action = CabinCustomerForgotPasswordFragmentDirections.actionForgotPasswordToResetPassword()
        activity!!.findNavController(R.id.customer_registration_navhost).navigate(action)
    }

    //endregion
}