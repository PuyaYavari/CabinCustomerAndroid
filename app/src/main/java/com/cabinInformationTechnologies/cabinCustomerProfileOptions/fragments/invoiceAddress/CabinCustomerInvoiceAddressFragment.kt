package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.invoiceAddress

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
import com.cabinInformationTechnologies.cabin.R



class CabinCustomerInvoiceAddressFragment : com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment(),
    com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.invoiceAddress.CabinCustomerInvoiceAddressContracts.View {
    var presenter: com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.invoiceAddress.CabinCustomerInvoiceAddressContracts.Presenter? =
        com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.invoiceAddress.CabinCustomerInvoiceAddressPresenter(
            this
        )
    private lateinit var pageView: View

    private val args: CabinCustomerInvoiceAddressFragmentArgs by navArgs()

    private lateinit var provinceSpinner: Spinner
    private lateinit var districtSpinner: Spinner

    private var operationType =
        com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.invoiceAddress.CabinCustomerInvoiceAddressFragment.OperationType.ADD

    private var provinceSet =
        com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.invoiceAddress.CabinCustomerInvoiceAddressFragment.FieldSet.NO
    private var districtSet =
        com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.invoiceAddress.CabinCustomerInvoiceAddressFragment.FieldSet.NO

    override var provinces: List<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProvince?> = listOf()
        set(value) {
            field = value
            ArrayAdapter(
                this.context!!,
                R.layout.cabin_customer_province_district_spinner_selected_item,
                field
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(R.layout.cabin_customer_province_district_spinner_item)
                // Apply the adapter to the spinner
                provinceSpinner.adapter = adapter
                if (provinceSet == com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.invoiceAddress.CabinCustomerInvoiceAddressFragment.FieldSet.NO) {
                    val argProvince = args.address?.province
                    if (argProvince != null) {
                        var index = 0
                        var found = false
                        while (index < field.size && !found) {
                            val province = field[index].toString()
                            if (province == argProvince) {
                                provinceSpinner.setSelection(index)
                                found = true
                            }
                            index++
                        }
                    }
                    provinceSet =
                        com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.invoiceAddress.CabinCustomerInvoiceAddressFragment.FieldSet.YES
                }
            }

        }

    override var districts: List<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELDistrict?> = listOf()
        set(value) {
            field = value
            ArrayAdapter(
                this.context!!,
                R.layout.cabin_customer_province_district_spinner_selected_item,
                field
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(R.layout.cabin_customer_province_district_spinner_item)
                // Apply the adapter to the spinner
                districtSpinner.adapter = adapter
                if (districtSet == com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.invoiceAddress.CabinCustomerInvoiceAddressFragment.FieldSet.NO) {
                    val argDistrict = args.address?.district
                    if (argDistrict != null) {
                        var index = 0
                        var found = false
                        while (index < field.size && !found) {
                            val province = field[index].toString()
                            if (province == argDistrict) {
                                districtSpinner.setSelection(index)
                                found = true
                            }
                            index++
                        }
                    }
                    districtSet =
                        com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.invoiceAddress.CabinCustomerInvoiceAddressFragment.FieldSet.YES
                }
            }
        }

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
        if (args.address != null) {
            operationType =
                com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.invoiceAddress.CabinCustomerInvoiceAddressFragment.OperationType.EDIT
            provinceSet =
                com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.invoiceAddress.CabinCustomerInvoiceAddressFragment.FieldSet.NO
            districtSet =
                com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.invoiceAddress.CabinCustomerInvoiceAddressFragment.FieldSet.NO
        }

        pageView.findViewById<ImageButton>(R.id.invoice_address_back_button)
            .setOnClickListener { onBackPressed() }

        pageView.findViewById<EditText>(R.id.invoice_address_name).apply {
            if(presenter != null) filters = arrayOf(
                if (presenter != null) InputFilter.LengthFilter(com.cabinInformationTechnologies.cabinCustomerBase.Constants.MAX_NAME_LENGTH)
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
                if (presenter != null) InputFilter.LengthFilter(com.cabinInformationTechnologies.cabinCustomerBase.Constants.MAX_SURNAME_LENGTH)
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
                if (presenter != null) InputFilter.LengthFilter(com.cabinInformationTechnologies.cabinCustomerBase.Constants.MAX_PHONE_LENGTH)
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
        //FIXME: DOESN'T WORK AFTER A FIELD IS FOCUSED
        provinceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val province = parent?.getItemAtPosition(p2) as com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProvince
                val context = context
                if (context != null) {
                    presenter?.getDistrictsOfProvince(context, province)
                    presenter?.setProvince(province)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        districtSpinner = pageView.findViewById(R.id.invoice_address_district_spinner)
        //FIXME: DOESN'T WORK AFTER A FIELD IS FOCUSED
        districtSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val district = parent?.getItemAtPosition(p2) as com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELDistrict
                val context = context
                if (context != null) {
                    presenter?.setDistrict(district)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        val context = this.context
        if (context != null)
            presenter?.getProvinces(context)

        pageView.findViewById<EditText>(R.id.invoice_address_address).apply {
            if(presenter != null) filters = arrayOf(
                if (presenter != null) InputFilter.LengthFilter(com.cabinInformationTechnologies.cabinCustomerBase.Constants.MAX_ADDRESS_LENGTH)
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
                if (presenter != null) InputFilter.LengthFilter(com.cabinInformationTechnologies.cabinCustomerBase.Constants.MAX_ADDRESS_HEADER_LENGTH)
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
            .setOnClickListener { presenter?.setInvoiceType(false) }
        pageView.findViewById<RadioButton>(R.id.invoice_address_corporate_invoice_button)
            .setOnClickListener { presenter?.setInvoiceType(true) }

        pageView.findViewById<EditText>(R.id.invoice_address_corporation_name).apply {
            if(presenter != null) filters = arrayOf(
                if (presenter != null) InputFilter.LengthFilter(com.cabinInformationTechnologies.cabinCustomerBase.Constants.MAX_CORPORATION_NAME_LENGTH)
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
                if (presenter != null) InputFilter.LengthFilter(com.cabinInformationTechnologies.cabinCustomerBase.Constants.MAX_TAX_NUMBER_LENGTH)
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
                if (presenter != null) InputFilter.LengthFilter(com.cabinInformationTechnologies.cabinCustomerBase.Constants.MAX_TAX_ADMINISTRATION_LENGTH)
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
            val context = this.context
            if (context != null) {
                if (operationType == com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.invoiceAddress.CabinCustomerInvoiceAddressFragment.OperationType.ADD)
                    presenter?.saveAddress(context)
                else
                    presenter?.updateAddress(context)
            }
        }

        if (operationType == com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.invoiceAddress.CabinCustomerInvoiceAddressFragment.OperationType.EDIT) {
            val isCorporate = args.address?.isCorporate
            if (isCorporate != null)
                presenter?.setInvoiceType(isCorporate)
            setupInitialData()
        }
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
        presenter?.setId(args.address?.id)
        pageView.findViewById<EditText>(R.id.invoice_address_name).setText(args.address?.name)
        pageView.findViewById<EditText>(R.id.invoice_address_surname).setText(args.address?.surname)
        pageView.findViewById<EditText>(R.id.invoice_address_phone).setText(args.address?.phone?.substring(4))
        pageView.findViewById<EditText>(R.id.invoice_address_address).setText(args.address?.address)
        pageView.findViewById<EditText>(R.id.invoice_address_address_header).setText(args.address?.header)
        val isCorpotate = args.address?.isCorporate
        if (isCorpotate != null && isCorpotate) {
            pageView.findViewById<RadioGroup>(R.id.invoice_address_redio_group).check(R.id.invoice_address_corporate_invoice_button)
            pageView.findViewById<EditText>(R.id.invoice_address_corporation_name).setText(args.address?.corporationName)
            pageView.findViewById<EditText>(R.id.invoice_address_tax_number).setText(args.address?.taxNumber)
            pageView.findViewById<EditText>(R.id.invoice_address_tax_administration).setText(args.address?.taxAdministration)
        } else {
            pageView.findViewById<RadioGroup>(R.id.invoice_address_redio_group).check(R.id.invoice_address_personal_invoice_button)
        }
    }

    private enum class FieldSet {
        NO, YES
    }

    private enum class OperationType {
        ADD, EDIT
    }

    //endregion
}