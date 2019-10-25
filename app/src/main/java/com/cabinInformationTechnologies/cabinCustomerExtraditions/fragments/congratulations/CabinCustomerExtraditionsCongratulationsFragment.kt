package com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.congratulations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cabinInformationTechnologies.cabin.R

class CabinCustomerExtraditionsCongratulationsFragment : com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment(),
    com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.congratulations.CabinCustomerExtraditionsCongratulationsContracts.View {

    var presenter: com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.congratulations.CabinCustomerExtraditionsCongratulationsContracts.Presenter? =
        com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.congratulations.CabinCustomerExtraditionsCongratulationsPresenter(
            this
        )
    private lateinit var pageView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_extraditions_congratulations, container, false)
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
        (activity as com.cabinInformationTechnologies.cabinCustomerExtraditions.CabinCustomerExtraditionsActivity).hideHeaderHelperText()
        (activity as com.cabinInformationTechnologies.cabinCustomerExtraditions.CabinCustomerExtraditionsActivity).showCross()
        (activity as com.cabinInformationTechnologies.cabinCustomerExtraditions.CabinCustomerExtraditionsActivity).hideBackArrow()

    }

    //TODO: Implement your View methods here

    //endregion
}