package com.cabinInformationTechnologies.cabinCustomerMeasure

import android.os.Bundle
import com.cabinInformationTechnologies.cabin.R

class CabinCustomerMeasureActivity : com.cabinInformationTechnologies.cabinCustomerBase.BaseActivity(),
    com.cabinInformationTechnologies.cabinCustomerMeasure.CabinCustomerMeasureContracts.View {

    var presenter: com.cabinInformationTechnologies.cabinCustomerMeasure.CabinCustomerMeasureContracts.Presenter? =
        com.cabinInformationTechnologies.cabinCustomerMeasure.CabinCustomerMeasurePresenter(this)

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cabin_customer_measure)
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

    //TODO: Implement your View methods here

    //endregion
}