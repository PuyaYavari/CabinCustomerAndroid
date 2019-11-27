package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.address

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress
import com.cabinInformationTechnologies.cabinCustomerFinishTrade.CabinCustomerFinishTradeActivity
import com.cabinInformationTechnologies.cabinCustomerFinishTrade.CabinCustomerFinishTradeContracts

class CabinCustomerFinishTradeAddressFragment(
    val callback: CabinCustomerFinishTradeContracts.ChangeAddAddressCallback) : BaseFragment(),
    CabinCustomerFinishTradeAddressContracts.View {

    var presenter: CabinCustomerFinishTradeAddressContracts.Presenter? =
        CabinCustomerFinishTradeAddressPresenter(
            this
        )
    private lateinit var pageView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_finish_trade_address, container, false)
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
        val context = this.context
        if (context != null)
            presenter?.getAddresses(context)

        pageView.findViewById<Spinner>(R.id.finish_trade_address_delivery_address_spinner)
            .onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                presenter?.deliveryAddress = parent?.getItemAtPosition(p2) as MODELAddress
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        pageView.findViewById<Spinner>(R.id.finish_trade_address_invoice_address_spinner)
            .onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                presenter?.invoiceAddress = parent?.getItemAtPosition(p2) as MODELAddress
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        pageView.findViewById<CheckBox>(R.id.finish_trade_address_delivery_address_use_as_invoice_address_checkbox)
            .setOnCheckedChangeListener { _, isChecked ->
                presenter?.setUseDelivery(isChecked)
                if (isChecked)
                    pageView.findViewById<LinearLayout>(R.id.finish_trade_address_invoice_address_layout)
                        .visibility = View.GONE
                else
                    pageView.findViewById<LinearLayout>(R.id.finish_trade_address_invoice_address_layout)
                        .visibility = View.VISIBLE
            }

        pageView.findViewById<TextView>(R.id.finish_trade_address_delivery_address_add)
            .setOnClickListener {
                callback.Delivery(null)
            }

        pageView.findViewById<TextView>(R.id.finish_trade_address_invoice_address_add)
            .setOnClickListener {
                callback.Invoice(null)
            }

        pageView.findViewById<LinearLayout>(R.id.finish_trade_address_delivery_address_detail_layout)
            .setOnClickListener {
                callback.Delivery(getSelectedDeliveryAddress())
            }

        pageView.findViewById<LinearLayout>(R.id.finish_trade_address_invoice_address_detail_layout)
            .setOnClickListener {
                callback.Invoice(getSelectedInvoiceAddress())
            }
    }

    override fun setDeliveryAddresses(addresses: List<MODELAddress?>) {
        ArrayAdapter(
            this.context!!,
            R.layout.cabin_customer_finish_trade_spinner_item,
            addresses
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.cabin_customer_province_district_spinner_item)
            // Apply the adapter to the spinner
            pageView.findViewById<Spinner>(R.id.finish_trade_address_delivery_address_spinner)
                .adapter = adapter
        }

        pageView.findViewById<LinearLayout>(R.id.finish_trade_address_delivery_address_detail_layout)
            .visibility = View.VISIBLE
        pageView.findViewById<Spinner>(R.id.finish_trade_address_delivery_address_spinner)
            .visibility = View.VISIBLE
        pageView.findViewById<View>(R.id.finish_trade_address_delivery_address_header_separator)
            .visibility = View.VISIBLE
        pageView.findViewById<CheckBox>(R.id.finish_trade_address_delivery_address_use_as_invoice_address_checkbox)
            .visibility = View.VISIBLE
    }

    override fun setInvoiceAddresses(addresses: List<MODELAddress?>) {
        ArrayAdapter(
            this.context!!,
            R.layout.cabin_customer_finish_trade_spinner_item,
            addresses
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.cabin_customer_province_district_spinner_item)
            // Apply the adapter to the spinner
            pageView.findViewById<Spinner>(R.id.finish_trade_address_invoice_address_spinner)
                .adapter = adapter
        }

        pageView.findViewById<LinearLayout>(R.id.finish_trade_address_invoice_address_detail_layout)
            .visibility = View.VISIBLE
        pageView.findViewById<Spinner>(R.id.finish_trade_address_invoice_address_spinner)
            .visibility = View.VISIBLE
        pageView.findViewById<View>(R.id.finish_trade_address_invoice_address_header_separator)
            .visibility = View.VISIBLE
    }

    @SuppressLint("SetTextI18n", "DefaultLocale")
    override fun showDeliveryAddressDetail(address: MODELAddress) {
        pageView.findViewById<TextView>(R.id.finish_trade_address_delivery_address_name).text =
            address.name
        pageView.findViewById<TextView>(R.id.finish_trade_address_delivery_address_address).text =
            address.address
        pageView.findViewById<TextView>(R.id.finish_trade_address_delivery_address_province_district).text =
            "${address.district.toLowerCase().capitalize()}/${address.province.toLowerCase().capitalize()}"
        pageView.findViewById<TextView>(R.id.finish_trade_address_delivery_address_phone).text =
            address.phone
    }

    @SuppressLint("SetTextI18n", "DefaultLocale")
    override fun showInvoiceAddressDetail(address: MODELAddress) {
        if (address.isCorporate) {
            pageView.findViewById<TextView>(R.id.finish_trade_address_invoice_address_name).text =
                address.corporationName
            pageView.findViewById<TextView>(R.id.finish_trade_address_invoice_address_tax_info).apply {
                visibility = View.VISIBLE
                text = "${address.taxAdministration?.toLowerCase()?.capitalize()}/${address.taxNumber}"
            }
        } else {
            pageView.findViewById<TextView>(R.id.finish_trade_address_invoice_address_name).text =
                address.name
            pageView.findViewById<TextView>(R.id.finish_trade_address_invoice_address_tax_info).apply {
                text = ""
                visibility = View.GONE
            }
        }
        pageView.findViewById<TextView>(R.id.finish_trade_address_invoice_address_address).text =
            address.address
        pageView.findViewById<TextView>(R.id.finish_trade_address_invoice_address_province_district).text =
            "${address.district.toLowerCase().capitalize()}/${address.province.toLowerCase().capitalize()}"
        pageView.findViewById<TextView>(R.id.finish_trade_address_invoice_address_phone).text =
            address.phone
    }

    override fun setActivityDeliveryAddress(address: MODELAddress?) {
        (activity as CabinCustomerFinishTradeActivity).setDeliveryAddress(address)
    }

    override fun setActivityInvoiceAddress(address: MODELAddress?) {
        (activity as CabinCustomerFinishTradeActivity).setInvoiceAddress(address)
    }

    override fun getSelectedDeliveryAddress(): MODELAddress? {
        return pageView.findViewById<Spinner>(R.id.finish_trade_address_delivery_address_spinner)
            .selectedItem as MODELAddress?
    }

    override fun getSelectedInvoiceAddress(): MODELAddress? {
        return pageView.findViewById<Spinner>(R.id.finish_trade_address_invoice_address_spinner)
            .selectedItem as MODELAddress?
    }

    override fun setupNoDeliveryAddress() {
        pageView.findViewById<TextView>(R.id.finish_trade_address_delivery_address_name)
            .text = ""
        pageView.findViewById<TextView>(R.id.finish_trade_address_delivery_address_address)
            .text = ""
        pageView.findViewById<TextView>(R.id.finish_trade_address_delivery_address_province_district)
            .text = ""
        pageView.findViewById<TextView>(R.id.finish_trade_address_delivery_address_phone)
            .text = ""

        pageView.findViewById<LinearLayout>(R.id.finish_trade_address_delivery_address_detail_layout)
            .visibility = View.GONE
        pageView.findViewById<Spinner>(R.id.finish_trade_address_delivery_address_spinner)
            .visibility = View.GONE
        pageView.findViewById<View>(R.id.finish_trade_address_delivery_address_header_separator)
            .visibility = View.INVISIBLE
        pageView.findViewById<View>(R.id.finish_trade_address_invoice_address_header_separator)
            .visibility = View.GONE
    }

    override fun setupNoInvoiceAddress() {
        pageView.findViewById<TextView>(R.id.finish_trade_address_invoice_address_name)
            .text = ""
        pageView.findViewById<TextView>(R.id.finish_trade_address_invoice_address_address)
            .text = ""
        pageView.findViewById<TextView>(R.id.finish_trade_address_invoice_address_province_district)
            .text = ""
        pageView.findViewById<TextView>(R.id.finish_trade_address_invoice_address_phone)
            .text = ""
        pageView.findViewById<TextView>(R.id.finish_trade_address_invoice_address_tax_info).apply {
            text = ""
            visibility = View.GONE
        }

        pageView.findViewById<LinearLayout>(R.id.finish_trade_address_invoice_address_detail_layout)
            .visibility = View.GONE
        pageView.findViewById<Spinner>(R.id.finish_trade_address_invoice_address_spinner)
            .visibility = View.GONE
        pageView.findViewById<View>(R.id.finish_trade_address_invoice_address_header_separator)
            .visibility = View.INVISIBLE
    }

    override fun showDeliveryAdd() {
        pageView.findViewById<TextView>(R.id.finish_trade_address_delivery_address_add)
            .visibility = View.VISIBLE
    }

    override fun hideDeliveryAdd() {
        pageView.findViewById<TextView>(R.id.finish_trade_address_delivery_address_add)
            .visibility = View.GONE
    }

    override fun closeActivity() {
        activity?.finish()
    }

    //endregion
}