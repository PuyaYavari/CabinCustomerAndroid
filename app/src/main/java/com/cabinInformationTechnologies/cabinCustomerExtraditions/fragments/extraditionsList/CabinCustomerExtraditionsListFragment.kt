package com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.R

class CabinCustomerExtraditionsListFragment : com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment(),
    com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.CabinCustomerExtraditionsListContracts.View {

    var presenter: com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.CabinCustomerExtraditionsListContracts.Presenter? =
        com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.CabinCustomerExtraditionsListPresenter(
            this
        )
    private lateinit var pageView: View
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_extraditions_list, container, false)
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
        (activity as com.cabinInformationTechnologies.cabinCustomerExtraditions.CabinCustomerExtraditionsActivity).showBackArrow()
        (activity as com.cabinInformationTechnologies.cabinCustomerExtraditions.CabinCustomerExtraditionsActivity).hideCross()

        pageView.findViewById<Button>(R.id.extraditions_list_footer_add_button)
            .setOnClickListener { presenter?.addExtradition() }

        recyclerView = pageView.findViewById(R.id.extraditions_list_recyclerview)
        presenter?.setupPage()
    }

    override fun addExtraditionListener() {
        presenter?.addExtradition()
    }

    override fun setupNoExtraditionList() {
        pageView.findViewById<Button>(R.id.extraditions_list_footer_add_button).apply {
            isClickable = false
            isEnabled = false
            isFocusable = false
        }
        pageView.findViewById<ConstraintLayout>(R.id.extraditions_list_footer_layout).visibility = View.GONE

        val myDataset: List<com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.CabinCustomerExtraditionsListContracts.ExtraditionBox> = listOf(
            com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.adapter.NoExtraditionBox()
        )

        val viewAdapter =
            com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.adapter.CabinCustomerExtraditionsListAdapter(
                this,
                myDataset
            )
        val viewManager = LinearLayoutManager(this.context)

        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun setupExtraditionsList() { //TODO: BASED ON DATA FROM INTERACTOR
        pageView.findViewById<Button>(R.id.extraditions_list_footer_add_button).apply {
            isClickable = true
            isEnabled = true
            isFocusable = true
        }
        pageView.findViewById<ConstraintLayout>(R.id.extraditions_list_footer_layout).visibility = View.VISIBLE

        val myDataset: MutableList<com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.CabinCustomerExtraditionsListContracts.ExtraditionBox> = mutableListOf()

        myDataset.add(
            com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.adapter.ExtraditionBox(
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.adapter.ExtraditionStatusIDs.ONGOING
            )
        )
        myDataset.add(
            com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.adapter.ExtraditionBox(
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.adapter.ExtraditionStatusIDs.ACCEPTED
            )
        )
        myDataset.add(
            com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.adapter.ExtraditionBox(
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.adapter.ExtraditionStatusIDs.DENIED
            )
        )

        val viewAdapter =
            com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.adapter.CabinCustomerExtraditionsListAdapter(
                this,
                myDataset
            )
        val viewManager = LinearLayoutManager(this.context)

        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun moveToExtraditionDetail() {
        presenter?.moveToExtraditionDetail()
    }

    override fun moveToExtraditionDetailAccepted() {
        presenter?.moveToExtraditionDetailAccepted()
    }

    override fun moveToExtraditionDetailDenied() {
        presenter?.moveToExtraditionDetailDenied()
    }

    //endregion
}