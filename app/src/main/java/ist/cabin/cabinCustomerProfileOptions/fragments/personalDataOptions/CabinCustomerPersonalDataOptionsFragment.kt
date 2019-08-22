package ist.cabin.cabinCustomerProfileOptions.fragments.personalDataOptions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabincustomer.R
import android.widget.DatePicker
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.widget.Toast
import ist.cabin.cabincustomer.MainActivity



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

    private fun setupPage() {
        pageView.findViewById<ImageButton>(R.id.personal_data_options_back_button)
            .setOnClickListener { activity!!.onBackPressed() }
        pageView.findViewById<EditText>(R.id.personal_data_options_birthday_input)
            .setOnClickListener {
                val cldr = Calendar.getInstance()
                val day = cldr.get(Calendar.DAY_OF_MONTH)
                val month = cldr.get(Calendar.MONTH)
                val year = cldr.get(Calendar.YEAR)
                // date picker dialog
                val picker: DatePickerDialog? = DatePickerDialog(this.activity!!,
                    DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                        pageView.findViewById<EditText>(R.id.personal_data_options_birthday_input).setText(
                            dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year
                        )
                    }, year, month, day
                )
                picker?.show()
            }
    }

    //endregion
}