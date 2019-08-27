package ist.cabin.cabinCustomerProfileOptions.fragments.notificationChoices

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Switch
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabincustomer.R

class CabinCustomerNotificationChoicesFragment : BaseFragment(), CabinCustomerNotificationChoicesContracts.View {

    var presenter: CabinCustomerNotificationChoicesContracts.Presenter? =
        CabinCustomerNotificationChoicesPresenter(this)
    private lateinit var pageView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_notification_choices, container, false)
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
        pageView.findViewById<ImageButton>(R.id.notification_choices_back_button)
            .setOnClickListener { activity!!.onBackPressed() }

        setInitialStates()

        pageView.findViewById<Switch>(R.id.notification_choices_email_switch)
            .setOnCheckedChangeListener { _, isChecked ->
                presenter?.setEmail(isChecked)
            }

        pageView.findViewById<Switch>(R.id.notification_choices_phone_switch)
            .setOnCheckedChangeListener { _, isChecked ->
                presenter?.setPhone(isChecked)
            }

        pageView.findViewById<Switch>(R.id.notification_choices_sms_switch)
            .setOnCheckedChangeListener { _, isChecked ->
                presenter?.setSMS(isChecked)
            }
    }

    private fun setInitialStates() {
        //TODO: RECEIVE DATA FROM BACKEND AND VISUALIZE
    }

    //endregion
}