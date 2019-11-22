package com.cabinInformationTechnologies.cabinCustomerRegistration

import android.content.Context
import android.os.Bundle
import com.cabinInformationTechnologies.cabin.R


class CabinCustomerRegistrationActivity : com.cabinInformationTechnologies.cabinCustomerBase.BaseActivity(),
    com.cabinInformationTechnologies.cabinCustomerRegistration.CabinCustomerRegistrationContracts.View {

    var presenter: com.cabinInformationTechnologies.cabinCustomerRegistration.CabinCustomerRegistrationContracts.Presenter? =
        com.cabinInformationTechnologies.cabinCustomerRegistration.CabinCustomerRegistrationPresenter(this)

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

    override fun setActiveUser(user: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELUser?) {
        val sharedPref = this.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putInt("userId", com.cabinInformationTechnologies.cabinCustomerBase.GlobalData.userId)
        editor.putString("userSession", com.cabinInformationTechnologies.cabinCustomerBase.GlobalData.session)
        editor.putString("userEmail", com.cabinInformationTechnologies.cabinCustomerBase.GlobalData.userEmail)
        editor.apply()
    }

    //endregion
}