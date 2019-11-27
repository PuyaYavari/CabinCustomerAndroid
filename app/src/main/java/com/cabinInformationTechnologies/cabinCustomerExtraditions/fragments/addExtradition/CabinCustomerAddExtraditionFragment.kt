package com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition

import android.content.Context
import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.R


class CabinCustomerAddExtraditionFragment : com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment(),
    com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.CabinCustomerAddExtraditionContracts.View {

    var presenter: com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.CabinCustomerAddExtraditionContracts.Presenter? =
        com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.CabinCustomerAddExtraditionPresenter(
            this
        )
    private lateinit var pageView: View
    private lateinit var recyclerView: RecyclerView

    private lateinit var viewAdapter: com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.CabinCustomerAddExtraditionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_extraditions_add_extradition, container, false)
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

    override fun onBackPressed() {
        activity!!.onBackPressed()
    }

    //region View

    override fun getApplicationContext(): Context {
        return activity!!.applicationContext
    }

    private fun setupPage() {
        (activity as com.cabinInformationTechnologies.cabinCustomerExtraditions.CabinCustomerExtraditionsActivity).hideHeaderHelperText()
        (activity as com.cabinInformationTechnologies.cabinCustomerExtraditions.CabinCustomerExtraditionsActivity).showBackArrow()
        (activity as com.cabinInformationTechnologies.cabinCustomerExtraditions.CabinCustomerExtraditionsActivity).hideCross()

        recyclerView = pageView.findViewById(R.id.add_extradition_products_recyclerview)

        val myDataset: List<Int> = mutableListOf(1)


        viewAdapter =
            com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.CabinCustomerAddExtraditionAdapter(
                this,
                myDataset
            )
        val viewManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        pageView.findViewById<EditText>(R.id.add_extradition_extradition_reason_input).filters =
            arrayOf(
                if (presenter != null) InputFilter.LengthFilter(com.cabinInformationTechnologies.cabinCustomerBase.Constants.MAX_EXTRADITION_REASON_LENGTH)
                else InputFilter.LengthFilter(30))

        pageView.findViewById<Button>(R.id.add_extradition_add_button).setOnClickListener {
            presenter?.saveData()
            presenter?.moveToCongratulationsPage()
        }
    }

    override fun setupERListener() {

    }

    //endregion
}