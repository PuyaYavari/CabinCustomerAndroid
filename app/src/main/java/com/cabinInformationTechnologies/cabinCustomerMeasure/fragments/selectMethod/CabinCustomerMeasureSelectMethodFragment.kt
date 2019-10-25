package com.cabinInformationTechnologies.cabinCustomerMeasure.fragments.selectMethod

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.cabinInformationTechnologies.cabin.R

class CabinCustomerMeasureSelectMethodFragment : com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment(),
    com.cabinInformationTechnologies.cabinCustomerMeasure.fragments.selectMethod.CabinCustomerMeasureSelectMethodContracts.View {

    var presenter: com.cabinInformationTechnologies.cabinCustomerMeasure.fragments.selectMethod.CabinCustomerMeasureSelectMethodContracts.Presenter? =
        com.cabinInformationTechnologies.cabinCustomerMeasure.fragments.selectMethod.CabinCustomerMeasureSelectMethodPresenter(
            this
        )
    private lateinit var pageView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_measure_select_method, container, false)
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
        pageView.findViewById<Button>(R.id.measure_select_method_auto_button)
            .setOnClickListener { presenter?.moveToAutoMeasureTurorial() }
    }

    //TODO: Implement your View methods here

    //endregion
}