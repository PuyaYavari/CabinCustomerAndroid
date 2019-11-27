package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.notificationChoices

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Switch
import androidx.navigation.fragment.findNavController
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment

class CabinCustomerNotificationChoicesFragment : BaseFragment(),
    CabinCustomerNotificationChoicesContracts.View {

    var presenter: CabinCustomerNotificationChoicesContracts.Presenter? =
        CabinCustomerNotificationChoicesPresenter(
            this
        )
    private lateinit var pageView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_notification_choices, container, false)
        val context = context
        if (context != null)
            presenter?.receiveInitialData(context, findNavController())
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
    }

    override fun disableEmail() {
        pageView.findViewById<Switch>(R.id.notification_choices_email_switch)
            .setOnCheckedChangeListener(null)
        pageView.findViewById<Switch>(R.id.notification_choices_email_switch).isChecked = false
        val context = this.context
        if (context != null)
            pageView.findViewById<Switch>(R.id.notification_choices_email_switch)
                .setOnCheckedChangeListener { _, isChecked ->
                    presenter?.setEmail(context, isChecked)
                }
    }

    override fun disablePhone() {
        pageView.findViewById<Switch>(R.id.notification_choices_phone_switch)
            .setOnCheckedChangeListener(null)
        pageView.findViewById<Switch>(R.id.notification_choices_phone_switch).isChecked = false
        val context = this.context
        if (context != null)
            pageView.findViewById<Switch>(R.id.notification_choices_phone_switch)
                .setOnCheckedChangeListener { _, isChecked ->
                    presenter?.setPhone(context, isChecked)
                }
    }

    override fun disableSms() {
        pageView.findViewById<Switch>(R.id.notification_choices_sms_switch)
            .setOnCheckedChangeListener(null)
        pageView.findViewById<Switch>(R.id.notification_choices_sms_switch).isChecked = false
        val context = this.context
        if (context != null)
            pageView.findViewById<Switch>(R.id.notification_choices_sms_switch)
                .setOnCheckedChangeListener { _, isChecked ->
                    presenter?.setSMS(context, isChecked)
                }
    }

    override fun enableEmail() {
        pageView.findViewById<Switch>(R.id.notification_choices_email_switch)
            .setOnCheckedChangeListener(null)
        pageView.findViewById<Switch>(R.id.notification_choices_email_switch).isChecked = true
        val context = this.context
        if (context != null)
            pageView.findViewById<Switch>(R.id.notification_choices_email_switch)
                .setOnCheckedChangeListener { _, isChecked ->
                    presenter?.setEmail(context, isChecked)
                }
    }

    override fun enablePhone() {
        pageView.findViewById<Switch>(R.id.notification_choices_phone_switch)
            .setOnCheckedChangeListener(null)
        pageView.findViewById<Switch>(R.id.notification_choices_phone_switch).isChecked = true
        val context = this.context
        if (context != null)
            pageView.findViewById<Switch>(R.id.notification_choices_phone_switch)
                .setOnCheckedChangeListener { _, isChecked ->
                    presenter?.setPhone(context, isChecked)
                }
    }

    override fun enableSms() {
        pageView.findViewById<Switch>(R.id.notification_choices_sms_switch)
            .setOnCheckedChangeListener(null)
        pageView.findViewById<Switch>(R.id.notification_choices_sms_switch).isChecked = true
        val context = this.context
        if (context != null)
            pageView.findViewById<Switch>(R.id.notification_choices_sms_switch)
                .setOnCheckedChangeListener { _, isChecked ->
                    presenter?.setSMS(context, isChecked)
                }
    }

    //endregion
}