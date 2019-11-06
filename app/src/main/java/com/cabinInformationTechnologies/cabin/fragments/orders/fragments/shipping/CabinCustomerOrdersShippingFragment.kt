package com.cabinInformationTechnologies.cabin.fragments.orders.fragments.shipping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabin.fragments.orders.CabinCustomerOrdersAdapter
import com.cabinInformationTechnologies.cabin.fragments.orders.CabinCustomerOrdersContracts

class CabinCustomerOrdersShippingFragment : com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment(),
    CabinCustomerOrdersShippingFragmentContracts.View,
    CabinCustomerOrdersContracts.FragmentsView{

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    var presenter: CabinCustomerOrdersShippingFragmentContracts.Presenter? =
        CabinCustomerOrdersShippingFragmentPresenter(
            this
        )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.cabin_customer_orders_main, container, false)

        val myDataset = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19") //TODO: remove
        viewManager = LinearLayoutManager(this.context)
        viewAdapter = CabinCustomerOrdersAdapter(this, myDataset)

        recyclerView = view.findViewById<RecyclerView>(R.id.main_orders_recyclerview).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        return view
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

    override fun orderboxOnClickListener() {
        presenter?.showOrderDetails()
    }

    //endregion
}