@file:Suppress("DEPRECATION")

package ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions.adapter.AddressTypeID
import ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions.adapter.CabinCustomerAddressOptionsAdapter
import ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions.adapter.NoAddressBox
import ist.cabin.cabincustomer.R

class CabinCustomerAddressOptionsFragment : BaseFragment(), CabinCustomerAddressOptionsContracts.View {

    var presenter: CabinCustomerAddressOptionsContracts.Presenter? = CabinCustomerAddressOptionsPresenter(this)
    private lateinit var pageView: View
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_address_options, container, false)
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
        pageView.findViewById<ImageButton>(R.id.address_options_back_button)
            .setOnClickListener { activity!!.onBackPressed() }
        recyclerView = pageView.findViewById(R.id.address_options_recycler)
        pageView.findViewById<Button>(R.id.address_options_delivery_address_tab_button)
            .setOnClickListener { presenter?.setupDeliveryAddressList() }
        pageView.findViewById<Button>(R.id.address_options_invoice_address_tab_button)
            .setOnClickListener { presenter?.setupInvoiceAddressList() }
        presenter?.setupPage()
    }

    override fun setupEmptyDeliveryAddressList() {
        pageView.findViewById<Button>(R.id.address_options_delivery_address_tab_button).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                background = resources.getDrawable(R.drawable.address_options_tab_buttons_selected_background, context.theme)
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    background = resources.getDrawable(R.drawable.address_options_tab_buttons_selected_background)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setTextColor(resources.getColor(R.color.address_options_button_labels, context.theme))
            } else {
                setTextColor(resources.getColor(R.color.address_options_button_labels))
            }
        }

        pageView.findViewById<Button>(R.id.address_options_invoice_address_tab_button).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                background = resources.getDrawable(R.drawable.address_options_tab_buttons_unselected_background, context.theme)
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                background = resources.getDrawable(R.drawable.address_options_tab_buttons_unselected_background)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setTextColor(resources.getColor(R.color.address_options_tab_buttons_unselected_label, context.theme))
            } else {
                setTextColor(resources.getColor(R.color.address_options_tab_buttons_unselected_label))
            }
        }

        pageView.findViewById<Button>(R.id.address_options_footer_add_button).apply {
            isClickable = false
            isEnabled = false
            isFocusable = false
            setOnClickListener { presenter?.moveToAddDeliveryAddressPage(null,null,null,null,null,null,null) }
        }
        pageView.findViewById<ConstraintLayout>(R.id.address_options_footer_layout).visibility = View.GONE

        val myDataset: List<CabinCustomerAddressOptionsContracts.Addressbox> = listOf(NoAddressBox(AddressTypeID.DELIVERY))

        val viewAdapter = CabinCustomerAddressOptionsAdapter(this, myDataset)
        val viewManager = LinearLayoutManager(this.context)

        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun setupDeliveryAddressList() {
        pageView.findViewById<Button>(R.id.address_options_delivery_address_tab_button).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                background = resources.getDrawable(R.drawable.address_options_tab_buttons_selected_background, context.theme)
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                background = resources.getDrawable(R.drawable.address_options_tab_buttons_selected_background)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setTextColor(resources.getColor(R.color.address_options_button_labels, context.theme))
            } else {
                setTextColor(resources.getColor(R.color.address_options_button_labels))
            }
        }

        pageView.findViewById<Button>(R.id.address_options_invoice_address_tab_button).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                background = resources.getDrawable(R.drawable.address_options_tab_buttons_unselected_background, context.theme)
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                background = resources.getDrawable(R.drawable.address_options_tab_buttons_unselected_background)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setTextColor(resources.getColor(R.color.address_options_tab_buttons_unselected_label, context.theme))
            } else {
                setTextColor(resources.getColor(R.color.address_options_tab_buttons_unselected_label))
            }
        }

        pageView.findViewById<Button>(R.id.address_options_footer_add_button).apply {
            isClickable = true
            isEnabled = true
            isFocusable = true
            text = resources.getText(R.string.add_delivery_address)
            setOnClickListener { presenter?.moveToAddDeliveryAddressPage(null,null,null,null,null,null,null) }
        }

        val myDataset: MutableList<CabinCustomerAddressOptionsContracts.Addressbox> = mutableListOf()

//        myDataset.add(AddressBox(AddressTypeID.DELIVERY)) //TODO: REMOVE
//        myDataset.add(AddressBox(AddressTypeID.DELIVERY)) //TODO: REMOVE
//        myDataset.add(AddressBox(AddressTypeID.DELIVERY)) //TODO: REMOVE

        val viewAdapter = CabinCustomerAddressOptionsAdapter(this, myDataset)
        val viewManager = LinearLayoutManager(this.context)

        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun setupEmptyInvoiceAddressList() {
        pageView.findViewById<Button>(R.id.address_options_invoice_address_tab_button).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                background = resources.getDrawable(R.drawable.address_options_tab_buttons_selected_background, context.theme)
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                background = resources.getDrawable(R.drawable.address_options_tab_buttons_selected_background)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setTextColor(resources.getColor(R.color.address_options_button_labels, context.theme))
            } else {
                setTextColor(resources.getColor(R.color.address_options_button_labels))
            }
        }

        pageView.findViewById<Button>(R.id.address_options_delivery_address_tab_button).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                background = resources.getDrawable(R.drawable.address_options_tab_buttons_unselected_background, context.theme)
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                background = resources.getDrawable(R.drawable.address_options_tab_buttons_unselected_background)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setTextColor(resources.getColor(R.color.address_options_tab_buttons_unselected_label, context.theme))
            } else {
                setTextColor(resources.getColor(R.color.address_options_tab_buttons_unselected_label))
            }
        }

        pageView.findViewById<Button>(R.id.address_options_footer_add_button).apply {
            isClickable = false
            isEnabled = false
            isFocusable = false
            setOnClickListener { presenter?.moveToAddInvoiceAddressPage(null,null,null,
                null,null,null,null,null,null,null,null) }
        }
        pageView.findViewById<ConstraintLayout>(R.id.address_options_footer_layout).visibility = View.GONE

        val myDataset: List<CabinCustomerAddressOptionsContracts.Addressbox> = listOf(NoAddressBox(AddressTypeID.INVOICE))

        val viewAdapter = CabinCustomerAddressOptionsAdapter(this, myDataset)
        val viewManager = LinearLayoutManager(this.context)

        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun setupInvoiceAddressList() {
        pageView.findViewById<Button>(R.id.address_options_invoice_address_tab_button).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                background = resources.getDrawable(R.drawable.address_options_tab_buttons_selected_background, context.theme)
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                background = resources.getDrawable(R.drawable.address_options_tab_buttons_selected_background)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setTextColor(resources.getColor(R.color.address_options_button_labels, context.theme))
            } else {
                setTextColor(resources.getColor(R.color.address_options_button_labels))
            }
        }

        pageView.findViewById<Button>(R.id.address_options_delivery_address_tab_button).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                background = resources.getDrawable(R.drawable.address_options_tab_buttons_unselected_background, context.theme)
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                background = resources.getDrawable(R.drawable.address_options_tab_buttons_unselected_background)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setTextColor(resources.getColor(R.color.address_options_tab_buttons_unselected_label, context.theme))
            } else {
                setTextColor(resources.getColor(R.color.address_options_tab_buttons_unselected_label))
            }
        }

        pageView.findViewById<Button>(R.id.address_options_footer_add_button).apply {
            isClickable = true
            isEnabled = true
            isFocusable = true
            text = resources.getText(R.string.add_invoice_address)
            setOnClickListener { presenter?.moveToAddInvoiceAddressPage(null,null,null,
                null,null,null,null,null,null,null,null) }
        }

        val myDataset: MutableList<CabinCustomerAddressOptionsContracts.Addressbox> = mutableListOf()

//        myDataset.add(AddressBox(AddressTypeID.INVOICE)) //TODO: REMOVE
//        myDataset.add(AddressBox(AddressTypeID.INVOICE)) //TODO: REMOVE
//        myDataset.add(TaxInvoiceAddressBox()) //TODO: REMOVE

        val viewAdapter = CabinCustomerAddressOptionsAdapter(this, myDataset)
        val viewManager = LinearLayoutManager(this.context)

        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }


    override fun addDeliveryAddressListener(
        name: String?,
        surname: String?,
        phone: String?,
        province: String?,
        district: String?,
        address: String?,
        addressHeader: String?
    ) {
        presenter?.moveToAddDeliveryAddressPage(name, surname, phone, province, district, address, addressHeader)
    }

    override fun addInvoiceAddressListener(
        name: String?,
        surname: String?,
        phone: String?,
        province: String?,
        district: String?,
        address: String?,
        addressHeader: String?,
        isCorporate: Boolean?,
        corporationName: String?,
        taxNo: String?,
        taxAdministration: String?
    ) {
        presenter?.moveToAddInvoiceAddressPage(name, surname, phone, province, district, address,
            addressHeader, isCorporate, corporationName, taxNo, taxAdministration)
    }


    //endregion
}