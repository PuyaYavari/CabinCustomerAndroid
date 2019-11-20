package com.cabinInformationTechnologies.cabin.fragments.orders.fragments.sent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabin.fragments.orders.CabinCustomerOrdersAdapter
import com.cabinInformationTechnologies.cabin.fragments.orders.CabinCustomerOrdersContracts
import com.cabinInformationTechnologies.cabin.fragments.orders.PagesIDs
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELOrder


class CabinCustomerOrdersSentFragment (val manager: CabinCustomerOrdersContracts.FragmentsManager) :
    com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment(),
    CabinCustomerOrdersSentFragmentContracts.View,
    CabinCustomerOrdersContracts.FragmentsView, SwipeRefreshLayout.OnRefreshListener {

    private var mSwipeRefreshLayout: SwipeRefreshLayout? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: CabinCustomerOrdersAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    var presenter: CabinCustomerOrdersSentFragmentContracts.Presenter? =
        CabinCustomerOrdersSentFragmentPresenter(
            this
        )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.cabin_customer_orders_main, container, false)

        viewManager = LinearLayoutManager(this.context)
        viewAdapter = CabinCustomerOrdersAdapter(this, manager, PagesIDs.SENT_PAGE)

        recyclerView = view.findViewById<RecyclerView>(R.id.main_orders_recyclerview).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        // SwipeRefreshLayout
        mSwipeRefreshLayout = view.findViewById(R.id.main_orders_layout)
        mSwipeRefreshLayout!!.setOnRefreshListener(this)
        mSwipeRefreshLayout!!.setColorSchemeResources(
            R.color.colorPrimary,
            R.color.colorAccent,
            R.color.colorPrimaryDark
        )

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

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        if (isVisibleToUser)
            checkData()
        super.setUserVisibleHint(isVisibleToUser)
    }

    //region View

    override fun moveToOrderDetail(order: MODELOrder) {
        presenter?.moveToDetailsPage(order)
    }

    override fun checkData() {
        try {
            viewAdapter.checkData()
        } catch (exception: Exception) {
            val context = this.context
            if (context != null)
                Logger.verbose(
                    context,
                    this::class.java.name,
                    "",
                    exception
                )
        }
    }

    override fun onRefresh() {
        val context = this.context
        if (context != null)
            manager.refresh(context, mSwipeRefreshLayout, viewAdapter)
    }

    //endregion
}