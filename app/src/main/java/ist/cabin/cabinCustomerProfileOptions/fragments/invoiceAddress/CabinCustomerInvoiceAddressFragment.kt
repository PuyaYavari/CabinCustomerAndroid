package ist.cabin.cabinCustomerProfileOptions.fragments.invoiceAddress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabincustomer.R

class CabinCustomerInvoiceAddressFragment : BaseFragment(), CabinCustomerInvoiceAddressContracts.View {

    var presenter: CabinCustomerInvoiceAddressContracts.Presenter? = CabinCustomerInvoiceAddressPresenter(this)
    private lateinit var pageView: View
    private lateinit var provinceSpinner: Spinner
    private lateinit var districtSpinner: Spinner

    var provinces = arrayOf("istanbul", "izmir", "ankara", "izmir", "ankara", "izmir", "ankara"
        , "izmir", "ankara", "izmir", "ankara", "izmir", "ankara", "izmir", "ankara", "izmir", "ankara"
        , "izmir", "ankara", "izmir", "ankara", "izmir", "ankara", "izmir", "ankara", "izmir", "ankara") //TODO: REMOVE
    var districts = arrayOf("Beylikduzu", "Esenyurt", "Buyukcekmece") //TODO: REMOVE

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_invoice_address, container, false)
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
        provinceSpinner = pageView.findViewById(R.id.invoice_address_province_spinner)
        ArrayAdapter(
            this.context!!,
            R.layout.cabin_customer_province_district_spinner_selected_item,
            provinces
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.cabin_customer_province_district_spinner_item)
            // Apply the adapter to the spinner
            provinceSpinner.adapter = adapter
        }
        districtSpinner = pageView.findViewById(R.id.invoice_address_district_spinner)
        ArrayAdapter(
            this.context!!,
            R.layout.cabin_customer_province_district_spinner_selected_item,
            districts
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.cabin_customer_province_district_spinner_item)
            // Apply the adapter to the spinner
            districtSpinner.adapter = adapter
        }

        pageView.findViewById<ImageButton>(R.id.invoice_address_back_button)
            .setOnClickListener { activity!!.onBackPressed() }
        pageView.findViewById<RadioButton>(R.id.invoice_address_personal_invoice_button)
            .setOnClickListener { hideCorporateInvoiceData() }
        pageView.findViewById<RadioButton>(R.id.invoice_address_corporate_invoice_button)
            .setOnClickListener { showCorporateInvoiceData() }
        hideCorporateInvoiceData()
    }

    override fun showCorporateInvoiceData() {
        pageView.findViewById<LinearLayout>(R.id.invoice_address_corporate_invoice_layout)
            .visibility = View.VISIBLE
    }

    override fun hideCorporateInvoiceData() {
        pageView.findViewById<LinearLayout>(R.id.invoice_address_corporate_invoice_layout)
            .visibility = View.GONE
    }

    //endregion
}