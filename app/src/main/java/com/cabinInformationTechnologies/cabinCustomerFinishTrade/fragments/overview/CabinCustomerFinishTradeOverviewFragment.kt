package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.overview

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ScrollView
import android.widget.TextView
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAgreements
import com.cabinInformationTechnologies.cabinCustomerFinishTrade.CabinCustomerFinishTradeActivity


class CabinCustomerFinishTradeOverviewFragment : BaseFragment(),
    CabinCustomerFinishTradeOverviewContracts.View {

    var presenter: CabinCustomerFinishTradeOverviewContracts.Presenter? =
        CabinCustomerFinishTradeOverviewPresenter(
            this
        )
    private lateinit var pageView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_finish_trade_overview, container, false)
        setupPage()
        presenter?.listAgreements(
            this.context,
            (activity as CabinCustomerFinishTradeActivity).presenter?.orderId
        )
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

    @SuppressLint("SetTextI18n", "ClickableViewAccessibility")
    private fun setupPage() {
        pageView.apply {
            val deliveryAddress = (activity as CabinCustomerFinishTradeActivity).presenter?.deliveryAddress
            findViewById<TextView>(R.id.finish_trade_overview_delivery_address).text =
                "${deliveryAddress?.address} ${deliveryAddress?.district}/${deliveryAddress?.province}"
            val invoiceAddress = (activity as CabinCustomerFinishTradeActivity).presenter?.invoiceAddress
            findViewById<TextView>(R.id.finish_trade_overview_invoice_address).text =
                "${invoiceAddress?.address} ${invoiceAddress?.district}/${invoiceAddress?.province}"
            findViewById<TextView>(R.id.finish_trade_payment_sum_price).text =
                (activity as CabinCustomerFinishTradeActivity).presenter?.price.toString()
            findViewById<ScrollView>(R.id.finish_trade_overview_layout).setOnTouchListener { _, _ ->
                findViewById<TextView>(R.id.finish_trade_accept_preliminary_information_form).parent.requestDisallowInterceptTouchEvent(false)
                findViewById<TextView>(R.id.finish_trade_accept_distance_sales_agreement).parent.requestDisallowInterceptTouchEvent(false)
                false
            }

            findViewById<TextView>(R.id.finish_trade_accept_preliminary_information_form).setOnTouchListener { _, _ ->
                findViewById<TextView>(R.id.finish_trade_accept_preliminary_information_form).parent.requestDisallowInterceptTouchEvent(true)
                false
            }

            findViewById<TextView>(R.id.finish_trade_accept_distance_sales_agreement).setOnTouchListener { _, _ ->
                findViewById<TextView>(R.id.finish_trade_accept_distance_sales_agreement).parent.requestDisallowInterceptTouchEvent(true)
                false
            }
        }
    }

    override fun setAgreements(agreements: MODELAgreements) {
        pageView.apply {
            findViewById<TextView>(R.id.finish_trade_accept_preliminary_information_form).apply {
                text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Html.fromHtml(agreements.getPIF(), Html.FROM_HTML_MODE_COMPACT)
                } else {
                    Html.fromHtml(agreements.getPIF())
                }
                movementMethod = ScrollingMovementMethod()
            }
            findViewById<TextView>(R.id.finish_trade_accept_distance_sales_agreement).apply {
                text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Html.fromHtml(agreements.getDSA(), Html.FROM_HTML_MODE_COMPACT)
                } else {
                    Html.fromHtml(agreements.getDSA())
                }
                movementMethod = ScrollingMovementMethod()
            }
            findViewById<CheckBox>(R.id.finish_trade_accept_preliminary_information_form_checkbox)
                .setOnCheckedChangeListener { _, isChecked ->
                    (activity as CabinCustomerFinishTradeActivity).presenter?.PIFAccepted = isChecked
                }
            findViewById<CheckBox>(R.id.finish_trade_accept_distance_sales_agreement_checkbox)
                .setOnCheckedChangeListener { _, isChecked ->
                    (activity as CabinCustomerFinishTradeActivity).presenter?.DSAAccepted = isChecked
                }
        }
    }

    //endregion
}