package com.cabinInformationTechnologies.cabinCustomerAgreement.fragments.agreement

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.cabinInformationTechnologies.cabin.R

class CabinCustomerAgreementFragment : com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment(),
    com.cabinInformationTechnologies.cabinCustomerAgreement.fragments.agreement.CabinCustomerAgreementFragmentContracts.View {

    var presenter: com.cabinInformationTechnologies.cabinCustomerAgreement.fragments.agreement.CabinCustomerAgreementFragmentContracts.Presenter? =
        com.cabinInformationTechnologies.cabinCustomerAgreement.fragments.agreement.CabinCustomerAgreementFragmentPresenter(
            this
        )
    private lateinit var listener: com.cabinInformationTechnologies.cabinCustomerAgreement.fragments.agreement.CabinCustomerAgreementFragment.AgreementFragmentListener
    private lateinit var pageView: View


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is com.cabinInformationTechnologies.cabinCustomerAgreement.fragments.agreement.CabinCustomerAgreementFragment.AgreementFragmentListener) {
            listener = context
        } else {
            throw ClassCastException(
                context.toString() + " must implement AgreementFragmentListener.")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_register_agreement, container, false)
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
        pageView.findViewById<Button>(R.id.agreement_back_button).setOnClickListener { listener.moveBackToRegisteration() }
        pageView.findViewById<Button>(R.id.agreement_confirmation_button).setOnClickListener { presenter?.accept() }
    }

    //endregion

    interface AgreementFragmentListener {
        fun moveBackToRegisteration()
    }
}