package com.cabinInformationTechnologies.cabin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts

class MainRouter(var activity : Activity?) : MainContracts.Router {
    override fun unregister() {
        activity = null
    }

    override fun moveToProfileOptions() {
        val customerProfileOptionsActivity: BaseContracts.View =
            com.cabinInformationTechnologies.cabinCustomerProfileOptions.CabinCustomerProfileOptionsActivity()
        val intent = Intent(activity, customerProfileOptionsActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(activity!!.applicationContext, intent, Bundle.EMPTY)
    }

    override fun moveToMeasure() {
        val customerMeasureActivity: BaseContracts.View =
            com.cabinInformationTechnologies.cabinCustomerMeasure.CabinCustomerMeasureActivity()
        val intent = Intent(activity, customerMeasureActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(activity!!.applicationContext, intent, Bundle.EMPTY)
    }

    override fun moveToExtraditions() {
        val customerExtraditionsActivity: BaseContracts.View =
            com.cabinInformationTechnologies.cabinCustomerExtraditions.CabinCustomerExtraditionsActivity()
        val intent = Intent(activity, customerExtraditionsActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(activity!!.applicationContext, intent, Bundle.EMPTY)
    }

    override fun moveToRegistration() {
        val customerLoginActivity: BaseContracts.View =
            com.cabinInformationTechnologies.cabinCustomerRegistration.CabinCustomerRegistrationActivity()
        val intent = Intent(activity, customerLoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(activity!!.applicationContext, intent, Bundle.EMPTY)
    }

}