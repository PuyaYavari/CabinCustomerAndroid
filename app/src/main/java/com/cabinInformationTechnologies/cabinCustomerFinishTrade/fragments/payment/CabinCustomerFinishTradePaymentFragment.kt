package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cabinInformationTechnologies.cabin.R

class CabinCustomerFinishTradePaymentFragment : com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment(),
    com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.payment.CabinCustomerFinishTradePaymentContracts.View {

    var presenter: com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.payment.CabinCustomerFinishTradePaymentContracts.Presenter? =
        com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.payment.CabinCustomerFinishTradePaymentPresenter(
            this
        )
    private lateinit var pageView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_finish_trade_payment, container, false)
        setupPage()
        return pageView
    }

    override fun onResume() {
        super.onResume()
        presenter?.onResume()
    }

    override fun onPause() {
        presenter?.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
        presenter = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.onCreate()
    }

    //region View

    private fun setupPage() {

    }

    //TODO: Implement your View methods here

    //endregion
}