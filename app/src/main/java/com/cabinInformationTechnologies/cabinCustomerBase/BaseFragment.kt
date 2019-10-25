package com.cabinInformationTechnologies.cabinCustomerBase

import android.content.Context
import android.content.ContextWrapper
//import android.support.v4.app.Fragment
import androidx.fragment.app.Fragment


abstract class BaseFragment: Fragment(), com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {

    fun getBaseActivity(): com.cabinInformationTechnologies.cabinCustomerBase.BaseActivity? {
        var context = context
        while (context is ContextWrapper) {
            if (context is com.cabinInformationTechnologies.cabinCustomerBase.BaseActivity) {
                return context
            }
            context = context.baseContext
        }
        return null
    }

    override fun getActivityContext(): Context? {
        return getBaseActivity()
    }

    override fun showErrorDialog(error: String?) {
        getBaseActivity()?.showErrorDialog(error)
    }
}