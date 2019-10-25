package com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionDetailAccepted

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cabinInformationTechnologies.cabin.R

class CabinCustomerExtraditionDetailAcceptedFragment : com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment(),
    com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionDetailAccepted.CabinCustomerExtraditionDetailAcceptedContracts.View {

    var presenter: com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionDetailAccepted.CabinCustomerExtraditionDetailAcceptedContracts.Presenter? =
        com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionDetailAccepted.CabinCustomerExtraditionDetailAcceptedPresenter(
            this
        )
    private lateinit var pageView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_extraditions_detail_accepted, container, false)
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

    }

    //TODO: Implement your View methods here

    //endregion
}