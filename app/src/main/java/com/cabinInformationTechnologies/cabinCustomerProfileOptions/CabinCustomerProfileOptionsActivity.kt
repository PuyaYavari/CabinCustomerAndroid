package com.cabinInformationTechnologies.cabinCustomerProfileOptions

import android.os.Bundle
import com.cabinInformationTechnologies.cabin.R

class CabinCustomerProfileOptionsActivity : com.cabinInformationTechnologies.cabinCustomerBase.BaseActivity(),
    com.cabinInformationTechnologies.cabinCustomerProfileOptions.CabinCustomerProfileOptionsContracts.View {

    var presenter: com.cabinInformationTechnologies.cabinCustomerProfileOptions.CabinCustomerProfileOptionsContracts.Presenter? =
        com.cabinInformationTechnologies.cabinCustomerProfileOptions.CabinCustomerProfileOptionsPresenter(this)

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cabin_customer_profile_options_main)
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

    //endregion
}