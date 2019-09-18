package ist.cabin.cabinCustomerProfileOptions.fragments.invoiceAddress

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.navArgs
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabinCustomerBase.Constants
import ist.cabin.cabincustomer.R

class CabinCustomerInvoiceAddressFragment : BaseFragment(), CabinCustomerInvoiceAddressContracts.View {

    var presenter: CabinCustomerInvoiceAddressContracts.Presenter? = CabinCustomerInvoiceAddressPresenter(this)
    private lateinit var pageView: View

    private val args: CabinCustomerInvoiceAddressFragmentArgs by navArgs()

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

    override fun onBackPressed() {
        activity!!.onBackPressed()
    }

    private fun setupPage() {
        pageView.findViewById<ImageButton>(R.id.invoice_address_back_button)
            .setOnClickListener { onBackPressed() }

        pageView.findViewById<EditText>(R.id.invoice_address_name).apply {
            if(presenter != null) filters = arrayOf(
                if (presenter != null) InputFilter.LengthFilter(Constants.MAX_NAME_LENGTH)
                else InputFilter.LengthFilter(30))
            addTextChangedListener(object :
                TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    presenter?.setName(p0.toString())
                }
                override fun afterTextChanged(p0: Editable?) {}
            })
        }

        pageView.findViewById<EditText>(R.id.invoice_address_surname).apply {
            if(presenter != null) filters = arrayOf(
                if (presenter != null) InputFilter.LengthFilter(Constants.MAX_SURNAME_LENGTH)
                else InputFilter.LengthFilter(30))
            addTextChangedListener(object :
                TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    presenter?.setSurname(p0.toString())
                }
                override fun afterTextChanged(p0: Editable?) {}
            })
        }

        pageView.findViewById<EditText>(R.id.invoice_address_phone).apply {
            filters = arrayOf(
                if (presenter != null) InputFilter.LengthFilter(Constants.MAX_PHONE_LENGTH)
                else InputFilter.LengthFilter(20), //TODO: LIMIT BASED ON PRESENTER.PHONE
                InputFilter { src, _, _, _, _, _ ->
                    if (src == "") { // for backspace
                        return@InputFilter src
                    }
                    if (text.length <= 1 && (text.toString() + src).matches(Regex("[a-zA-Z ]+"))  ||
                        (text.toString() + src).matches(Regex("^[123456789][0-9]*$"))
                    ) {
                        src
                    } else if (text.length > 1 ) {
                        src
                    } else {
                        ""
                    }
                })
            addTextChangedListener(PhoneNumberFormattingTextWatcher())
            addTextChangedListener( object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    presenter?.setPhone(p0.toString())
                }
                override fun afterTextChanged(p0: Editable?) {}
            })
        }

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

        pageView.findViewById<EditText>(R.id.invoice_address_address).apply {
            if(presenter != null) filters = arrayOf(
                if (presenter != null) InputFilter.LengthFilter(Constants.MAX_ADDRESS_LENGTH)
                else InputFilter.LengthFilter(300))
            addTextChangedListener(object :
                TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    presenter?.setAddress(p0.toString())
                }
                override fun afterTextChanged(p0: Editable?) {}
            })
        }

        pageView.findViewById<EditText>(R.id.invoice_address_address_header).apply {
            if(presenter != null) filters = arrayOf(
                if (presenter != null) InputFilter.LengthFilter(Constants.MAX_ADDRESS_HEADER_LENGTH)
                else InputFilter.LengthFilter(30))
            addTextChangedListener(object :
                TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    presenter?.setAddressHeader(p0.toString())
                }
                override fun afterTextChanged(p0: Editable?) {}
            })
        }

        pageView.findViewById<RadioButton>(R.id.invoice_address_personal_invoice_button)
            .setOnClickListener { presenter?.isCorporate(false) }
        pageView.findViewById<RadioButton>(R.id.invoice_address_corporate_invoice_button)
            .setOnClickListener { presenter?.isCorporate(true) }

        pageView.findViewById<EditText>(R.id.invoice_address_corporation_name).apply {
            if(presenter != null) filters = arrayOf(
                if (presenter != null) InputFilter.LengthFilter(Constants.MAX_CORPORATION_NAME_LENGTH)
                else InputFilter.LengthFilter(50))
            addTextChangedListener(object :
                TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    presenter?.setCorporationName(p0.toString())
                }
                override fun afterTextChanged(p0: Editable?) {}
            })
        }

        pageView.findViewById<EditText>(R.id.invoice_address_tax_number).apply {
            if(presenter != null) filters = arrayOf(
                if (presenter != null) InputFilter.LengthFilter(Constants.MAX_TAX_NUMBER_LENGTH)
                else InputFilter.LengthFilter(50))
            addTextChangedListener(object :
                TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    presenter?.setTaxNumber(p0.toString())
                }
                override fun afterTextChanged(p0: Editable?) {}
            })
        }

        pageView.findViewById<EditText>(R.id.invoice_address_tax_administration).apply {
            if(presenter != null) filters = arrayOf(
                if (presenter != null) InputFilter.LengthFilter(Constants.MAX_TAX_ADMINISTRATION_LENGTH)
                else InputFilter.LengthFilter(50))
            addTextChangedListener(object :
                TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    presenter?.setTaxAdministration(p0.toString())
                }
                override fun afterTextChanged(p0: Editable?) {}
            })
        }

        pageView.findViewById<Button>(R.id.invoice_address_add_button).setOnClickListener {
            presenter?.saveData()
            onBackPressed()
        }

        presenter?.isCorporate(args.isCorporate)
        setupInitialData()
    }

    override fun showCorporateInvoiceData() {
        pageView.findViewById<RadioButton>(R.id.invoice_address_corporate_invoice_button).isSelected = true
        pageView.findViewById<LinearLayout>(R.id.invoice_address_corporate_invoice_layout)
            .visibility = View.VISIBLE
    }

    override fun hideCorporateInvoiceData() {
        pageView.findViewById<RadioButton>(R.id.invoice_address_personal_invoice_button).isSelected = true
        pageView.findViewById<LinearLayout>(R.id.invoice_address_corporate_invoice_layout)
            .visibility = View.GONE
    }

    override fun enableAddButton() {
        pageView.findViewById<Button>(R.id.invoice_address_add_button).apply {
            isEnabled = true
            isClickable = true
            alpha = 1f
        }
    }

    override fun disableAddButton() {
        pageView.findViewById<Button>(R.id.invoice_address_add_button).apply {
            isEnabled = false
            isClickable = false
            alpha = 0.5f
        }
    }

    private fun setupInitialData() {
        pageView.findViewById<EditText>(R.id.invoice_address_name).setText(args.name)
        pageView.findViewById<EditText>(R.id.invoice_address_surname).setText(args.surname)
        pageView.findViewById<EditText>(R.id.invoice_address_phone).setText(args.phone)
        if (args.province != null)
            pageView.findViewById<EditText>(R.id.invoice_address_province_spinner).setText(args.province)
        if (args.district != null)
            pageView.findViewById<EditText>(R.id.invoice_address_district_spinner).setText(args.district)
        pageView.findViewById<EditText>(R.id.invoice_address_address).setText(args.address)
        pageView.findViewById<EditText>(R.id.invoice_address_address_header).setText(args.addressHeader)
        if (args.isCorporate) {
            pageView.findViewById<EditText>(R.id.invoice_address_corporation_name).setText(args.corporationName)
            pageView.findViewById<EditText>(R.id.invoice_address_tax_number).setText(args.taxNo)
            pageView.findViewById<EditText>(R.id.invoice_address_tax_administration).setText(args.taxAdministration)
        }
    }

    //endregion
}