@file:Suppress("DEPRECATION", "NAME_SHADOWING")

package ist.cabin.cabinCustomerProfileOptions.fragments.personalDataOptions

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabinCustomerBase.Constants
import ist.cabin.cabinCustomerBase.GlobalData
import ist.cabin.cabincustomer.R
import java.util.*


class CabinCustomerPersonalDataOptionsFragment : BaseFragment(), CabinCustomerPersonalDataOptionsContracts.View {

    var presenter: CabinCustomerPersonalDataOptionsContracts.Presenter? =
        CabinCustomerPersonalDataOptionsPresenter(this)
    private lateinit var pageView: View



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_personal_data_options, container, false)
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

    @SuppressLint("SetTextI18n")
    private fun setupPage() {
        pageView.findViewById<ImageButton>(R.id.personal_data_options_back_button)
            .setOnClickListener { onBackPressed() }

        pageView.findViewById<EditText>(R.id.personal_data_options_name_input).apply {
            filters = arrayOf(
                if (presenter != null) InputFilter.LengthFilter(Constants.MAX_NAME_LENGTH)
                else InputFilter.LengthFilter(30))
            addTextChangedListener( object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    presenter?.setName(p0.toString())
                }
                override fun afterTextChanged(p0: Editable?) {}
            })
        }

        pageView.findViewById<EditText>(R.id.personal_data_options_surname_input).apply {
            filters = arrayOf(
                if (presenter != null) InputFilter.LengthFilter(Constants.MAX_SURNAME_LENGTH)
                else InputFilter.LengthFilter(30))
            addTextChangedListener( object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    presenter?.setSurname(p0.toString())
                }
                override fun afterTextChanged(p0: Editable?) {}
            })
        }

        pageView.findViewById<EditText>(R.id.personal_data_options_phone_input).apply {
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            pageView.findViewById<EditText>(R.id.personal_data_options_birthday_input)
                .setOnClickListener {
                    val cldr = Calendar.getInstance()
                    val day = cldr.get(Calendar.DAY_OF_MONTH)
                    val month = cldr.get(Calendar.MONTH)
                    val year = cldr.get(Calendar.YEAR)
                    // date picker dialog
                    val picker: DatePickerDialog? = DatePickerDialog(
                        this.activity!!,
                        DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                            pageView.findViewById<EditText>(R.id.personal_data_options_birthday_input)
                                .setText(
                                    dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year
                                )
                            cldr.set(year, monthOfYear, dayOfMonth)
                            presenter?.setBirthday(dayOfMonth, monthOfYear, year)
                        }, year, month, day
                    )
                    picker?.show()
                }
        } else {
            pageView.findViewById<EditText>(R.id.personal_data_options_birthday_input)
                .setOnClickListener {
                    val cldr = java.util.Calendar.getInstance()
                    val day = cldr.get(java.util.Calendar.DAY_OF_MONTH)
                    val month = cldr.get(java.util.Calendar.MONTH)
                    val year = cldr.get(java.util.Calendar.YEAR)
                    // date picker dialog
                    val picker: DatePickerDialog? = DatePickerDialog(
                        this.activity!!,
                        DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                            pageView.findViewById<EditText>(R.id.personal_data_options_birthday_input)
                                .setText(
                                    dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year
                                )
                            cldr.set(year, monthOfYear, dayOfMonth)
                            presenter?.setBirthday(dayOfMonth, monthOfYear, year)
                        }, year, month, day
                    )
                    picker?.show()
                }
        }

        pageView.findViewById<EditText>(R.id.personal_data_options_email_input).apply {
            val userEmail = GlobalData.userEmail
            if (userEmail != null)
                presenter?.setEmail(userEmail)
            setText(GlobalData.userEmail)
            isClickable = false
            isFocusable = false
            filters = arrayOf(
                if (presenter != null) InputFilter.LengthFilter(Constants.MAX_EMAIL_LENGTH)
                else InputFilter.LengthFilter(50))
            addTextChangedListener( object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    presenter?.setEmail(p0.toString())
                }
                override fun afterTextChanged(p0: Editable?) {}
            })
        }

        pageView.findViewById<ImageButton>(R.id.personal_data_options_man_button)
            .setOnClickListener { presenter?.selectMan() }
        pageView.findViewById<ImageButton>(R.id.personal_data_options_woman_button)
            .setOnClickListener { presenter?.selectWoman() }

        pageView.findViewById<Button>(R.id.personal_data_options_save_button)
            .setOnClickListener {
                val context = this.context
                if (context != null)
                    presenter?.saveInputs(context)
            }

        val context = context
        if (context != null)
            presenter?.getInitialData(context)
    }

    override fun selectMan() {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                pageView.findViewById<ImageButton>(R.id.personal_data_options_man_button).background =
                    this.resources.getDrawable(R.drawable.personal_data_options_selected_man_button_background,
                        this.activity!!.theme)
                pageView.findViewById<ImageButton>(R.id.personal_data_options_woman_button).background =
                    this.resources.getDrawable(R.drawable.personal_data_options_unselected_sex_button_background,
                        this.activity!!.theme)
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN -> {
                pageView.findViewById<ImageButton>(R.id.personal_data_options_man_button).background =
                    this.resources.getDrawable(R.drawable.personal_data_options_selected_man_button_background)
                pageView.findViewById<ImageButton>(R.id.personal_data_options_woman_button).background =
                    this.resources.getDrawable(R.drawable.personal_data_options_unselected_sex_button_background)
            }
            else -> {
                pageView.findViewById<ImageButton>(R.id.personal_data_options_man_button).setBackgroundResource(
                    R.drawable.personal_data_options_selected_man_button_background)
                pageView.findViewById<ImageButton>(R.id.personal_data_options_woman_button).setBackgroundResource(
                    R.drawable.personal_data_options_unselected_sex_button_background)
            }
        }
        pageView.findViewById<ImageButton>(R.id.personal_data_options_man_button).setImageResource(R.drawable.icon_man_white)
        pageView.findViewById<ImageButton>(R.id.personal_data_options_woman_button).setImageResource(R.drawable.icon_woman_pink)
    }

    override fun selectWoman() {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                pageView.findViewById<ImageButton>(R.id.personal_data_options_man_button).background =
                    this.resources.getDrawable(R.drawable.personal_data_options_unselected_sex_button_background,
                        this.activity!!.theme)
                pageView.findViewById<ImageButton>(R.id.personal_data_options_woman_button).background =
                    this.resources.getDrawable(R.drawable.personal_data_options_selected_woman_button_background,
                        this.activity!!.theme)
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN -> {
                pageView.findViewById<ImageButton>(R.id.personal_data_options_man_button).background =
                    this.resources.getDrawable(R.drawable.personal_data_options_unselected_sex_button_background)
                pageView.findViewById<ImageButton>(R.id.personal_data_options_woman_button).background =
                    this.resources.getDrawable(R.drawable.personal_data_options_selected_woman_button_background)
            }
            else -> {
                pageView.findViewById<ImageButton>(R.id.personal_data_options_man_button).setBackgroundResource(
                    R.drawable.personal_data_options_unselected_sex_button_background)
                pageView.findViewById<ImageButton>(R.id.personal_data_options_woman_button).setBackgroundResource(
                    R.drawable.personal_data_options_selected_woman_button_background)
            }
        }
        pageView.findViewById<ImageButton>(R.id.personal_data_options_man_button).setImageResource(R.drawable.icon_man_blue)
        pageView.findViewById<ImageButton>(R.id.personal_data_options_woman_button).setImageResource(R.drawable.icon_woman_white)
    }

    override fun enableSaveButton() {
        pageView.findViewById<Button>(R.id.personal_data_options_save_button).apply {
            isClickable = true
            isEnabled = true
            alpha = 1f
        }
    }

    override fun disableSaveButton() {
        pageView.findViewById<Button>(R.id.personal_data_options_save_button).apply {
            isClickable = false
            isEnabled = false
            alpha = 0.5f
        }
    }

    override fun setName(name: String) {
        pageView.findViewById<EditText>(R.id.personal_data_options_name_input).setText(name)
    }

    override fun setSurname(surname: String) {
        pageView.findViewById<EditText>(R.id.personal_data_options_surname_input).setText(surname)
    }

    override fun setBirthday(date: Date) {
        pageView.findViewById<EditText>(R.id.personal_data_options_birthday_input).setText(
            "${date.date}/${date.month+1}/${date.year}"
        )
    }

    override fun setEmail(email: String) {
        pageView.findViewById<EditText>(R.id.personal_data_options_email_input).setText(email)
    }

    override fun setPhone(phone: String) {
        pageView.findViewById<EditText>(R.id.personal_data_options_phone_input).setText(phone)
    }

    override fun showSuccess(message: String?) {
        pageView.findViewById<TextView>(R.id.personal_data_options_feedback).apply {
            text = message ?: resources.getText(R.string.default_success_message)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setTextColor(resources.getColor(R.color.colorPriceGreen, this.context.theme))
            } else {
                setTextColor(resources.getColor(R.color.colorPriceGreen))
            }
            visibility = View.VISIBLE
            Handler().postDelayed({
                visibility = View.INVISIBLE
            }, 5000)
        }
    }

    override fun showFailure(message: String?) {
        pageView.findViewById<TextView>(R.id.personal_data_options_feedback).apply {
            text = message ?: resources.getText(R.string.default_error_message)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setTextColor(resources.getColor(R.color.colorStateRed, this.context.theme))
            } else {
                setTextColor(resources.getColor(R.color.colorStateRed))
            }
            visibility = View.VISIBLE
            Handler().postDelayed({
                visibility = View.INVISIBLE
            }, 5000)
        }
    }

    //endregion
}