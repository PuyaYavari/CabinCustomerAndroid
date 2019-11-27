package com.cabinInformationTechnologies.cabinCustomerRegistration

import android.content.Context
import android.os.Bundle
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.BaseActivity
import com.cabinInformationTechnologies.cabinCustomerBase.GlobalData
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELUser


class CabinCustomerRegistrationActivity : BaseActivity(),
    CabinCustomerRegistrationContracts.View {

    var presenter: CabinCustomerRegistrationContracts.Presenter? =
        CabinCustomerRegistrationPresenter(this)

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cabin_customer_registeration)



        presenter?.onCreate(intent.extras)
    }

    override fun onResume() {
        super.onResume()
        presenter?.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter?.onPause()
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

    //endregion

    //region View

    override fun setActiveUser(user: MODELUser?) {
        val sharedPref = this.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putInt("userId", GlobalData.userId)
        editor.putString("userSession", GlobalData.session)
        editor.putString("userEmail", GlobalData.userEmail)
        editor.apply()
    }
    //endregion
}