package ist.cabin.cabinCustomerProfileOptions.fragments.deliveryAddress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabincustomer.R

class CabinCustomerDeliveryAddressFragment : BaseFragment(), CabinCustomerDeliveryAddressContracts.View {

    var presenter: CabinCustomerDeliveryAddressContracts.Presenter? = CabinCustomerDeliveryAddressPresenter(this)
    private lateinit var pageView: View
    private lateinit var provinceSpinner: Spinner
    private lateinit var districtSpinner: Spinner

    var provinces = arrayOf("istanbul", "izmir", "ankara", "izmir", "ankara", "izmir", "ankara"
        , "izmir", "ankara", "izmir", "ankara", "izmir", "ankara", "izmir", "ankara", "izmir", "ankara"
        , "izmir", "ankara", "izmir", "ankara", "izmir", "ankara", "izmir", "ankara", "izmir", "ankara") //TODO: REMOVE
    var districts = arrayOf("Beylikduzu", "Esenyurt", "Buyukcekmece") //TODO: REMOVE

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_delivery_address, container, false)
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
        pageView.findViewById<ImageButton>(R.id.delivery_address_back_button)
            .setOnClickListener { activity!!.onBackPressed() }
        provinceSpinner = pageView.findViewById(R.id.delivery_address_province_spinner)
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
        districtSpinner = pageView.findViewById(R.id.delivery_address_district_spinner)
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
    }

    //TODO: Implement your View methods here

    //endregion
}