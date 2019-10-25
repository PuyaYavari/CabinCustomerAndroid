package com.cabinInformationTechnologies.cabinCustomerFinishTrade

import android.os.Bundle
import com.cabinInformationTechnologies.cabin.R





class CabinCustomerFinishTradeActivity : com.cabinInformationTechnologies.cabinCustomerBase.BaseActivity(),
    com.cabinInformationTechnologies.cabinCustomerFinishTrade.CabinCustomerFinishTradeContracts.View {

    var presenter: com.cabinInformationTechnologies.cabinCustomerFinishTrade.CabinCustomerFinishTradeContracts.Presenter? =
        com.cabinInformationTechnologies.cabinCustomerFinishTrade.CabinCustomerFinishTradePresenter(this)

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cabin_customer_finish_trade)
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