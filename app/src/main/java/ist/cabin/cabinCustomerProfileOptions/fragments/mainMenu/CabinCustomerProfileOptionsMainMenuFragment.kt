package ist.cabin.cabinCustomerProfileOptions.fragments.mainMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabincustomer.R

class CabinCustomerProfileOptionsMainMenuFragment : BaseFragment(), CabinCustomerProfileOptionsMainMenuContracts.View {

    var presenter: CabinCustomerProfileOptionsMainMenuContracts.Presenter? =
        CabinCustomerProfileOptionsMainMenuPresenter(this)
    private lateinit var pageView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_profile_options_main_menu, container, false)
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
        pageView.findViewById<ImageButton>(R.id.profile_options_main_menu_back_button)
            .setOnClickListener { activity!!.onBackPressed() }
        pageView.findViewById<CardView>(R.id.personal_data_box)
            .setOnClickListener { presenter?.moveToPersonalDataPage() }
        pageView.findViewById<CardView>(R.id.address_data_box)
            .setOnClickListener { presenter?.moveToAddressOptionsPage() }
        pageView.findViewById<CardView>(R.id.password_change_box)
            .setOnClickListener { presenter?.moveToChangePasswordPage() }
        pageView.findViewById<CardView>(R.id.notification_options_box)
            .setOnClickListener { presenter?.moveToNotificationChoicesPage() }
    }

    //endregion
}