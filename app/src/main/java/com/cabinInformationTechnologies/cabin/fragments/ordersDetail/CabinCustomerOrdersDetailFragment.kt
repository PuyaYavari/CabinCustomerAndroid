package com.cabinInformationTechnologies.cabin.fragments.ordersDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.MainActivity
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabin.fragments.ordersDetail.adapter.CabinCustomerOrdersDetailAdapter


class CabinCustomerOrdersDetailFragment : com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment(), CabinCustomerOrdersDetailContracts.View {
    private val args: CabinCustomerOrdersDetailFragmentArgs by navArgs()
    private lateinit var recyclerView: RecyclerView

    private lateinit var pageView: View
    var presenter: CabinCustomerOrdersDetailContracts.Presenter? = CabinCustomerOrdersDetailPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_orders_detail, container, false)
        return pageView
    }

    override fun onResume() {
        super.onResume()

        setupActivity()

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

    private fun setupActivity() {
        (activity!! as MainActivity).setHeader(resources.getString(R.string.orders_header),null)
        (activity!! as MainActivity).hideBackButton()
        (activity!! as MainActivity).lockDrawer()
        (activity!! as MainActivity).hideDrawerButton()
        (activity!! as MainActivity).showBackButton()
        (activity!! as MainActivity).hideClear()
        (activity!! as MainActivity).hideCross()
        (activity!! as MainActivity).hideProgressBar()
        (activity!! as MainActivity).hideRecyclerView()
        (activity!! as MainActivity).setHeaderColor(null as Int?)

        if (com.cabinInformationTechnologies.cabinCustomerBase.GlobalData.loggedIn) {
            setupPage()
            if ((activity!! as MainActivity).findViewById<ConstraintLayout>(R.id.blocker_layout)
                    .visibility == View.INVISIBLE) {
                (activity!! as MainActivity).layoutBackToDefault()
                (activity!! as MainActivity).showHeaderNavbar()
            } else
                (activity!! as MainActivity).unblockPage()
        } else
            (activity!! as MainActivity).showNeedLogin()
    }

    private fun setupPage() {
        recyclerView = pageView.findViewById(R.id.orders_detail_recyclerview)

        val pageTypeID = args.pageType
        val order = args.order

        pageView.findViewById<TextView>(R.id.orders_detail_order_id).text = order.getId().toString()

        presenter?.setupProperPage(pageTypeID, order)
    }

    override fun setupPendingPage(myDataset: MutableList<CabinCustomerOrdersDetailContracts.Detailbox>) {
        val viewAdapter = CabinCustomerOrdersDetailAdapter(this, myDataset)
        val viewManager = LinearLayoutManager(this.context)

        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        pageView.findViewById<TextView>(R.id.orders_addressbar_order_type_label).text = resources.getText(R.string.pending_orders_label)
    }

    override fun setupShippingPage(myDataset: MutableList<CabinCustomerOrdersDetailContracts.Detailbox>) {
        val viewAdapter = CabinCustomerOrdersDetailAdapter(this, myDataset)
        val viewManager = LinearLayoutManager(this.context)

        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        pageView.findViewById<TextView>(R.id.orders_addressbar_order_type_label).text = resources.getText(R.string.shipping_orders_label)
    }

    override fun setupSentPage(myDataset: MutableList<CabinCustomerOrdersDetailContracts.Detailbox>) {
        val viewAdapter = CabinCustomerOrdersDetailAdapter(this, myDataset)
        val viewManager = LinearLayoutManager(this.context)

        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        pageView.findViewById<TextView>(R.id.orders_addressbar_order_type_label).text = resources.getText(R.string.sent_orders_label)
    }

    override fun showProgressBar() {
        (activity!! as MainActivity).showProgressBar()
    }

    override fun hideProgressBar() {
        (activity!! as MainActivity).hideProgressBar()
    }

    //endregion
}