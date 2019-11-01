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
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabin.fragments.ordersDetail.adapter.*


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

        (activity!! as com.cabinInformationTechnologies.cabin.MainActivity).setHeader(resources.getString(R.string.orders_header),null)
        (activity!! as com.cabinInformationTechnologies.cabin.MainActivity).hideBackButton()
        (activity!! as com.cabinInformationTechnologies.cabin.MainActivity).lockDrawer()
        (activity!! as com.cabinInformationTechnologies.cabin.MainActivity).showBackButton()
        hideProgressBar()

        if (com.cabinInformationTechnologies.cabinCustomerBase.GlobalData.loggedIn) {
            setupPage()
            if ((activity!! as com.cabinInformationTechnologies.cabin.MainActivity).findViewById<ConstraintLayout>(R.id.blocker_layout)
                    .visibility == View.INVISIBLE) {
                (activity!! as com.cabinInformationTechnologies.cabin.MainActivity).layoutBackToDefault()
                (activity!! as com.cabinInformationTechnologies.cabin.MainActivity).showHeaderNavbar()
            } else
                (activity!! as com.cabinInformationTechnologies.cabin.MainActivity).unblockPage()
        } else
            (activity!! as com.cabinInformationTechnologies.cabin.MainActivity).showNeedLogin()

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
        recyclerView = pageView.findViewById(R.id.orders_detail_recyclerview)

        val pageTypeID = args.pageType
        presenter?.setupPropperPage(pageTypeID)
    }

    override fun setupPendingPage() {

        val buttikCount = 2 // TODO: Remove line
        val ordersCount = 4 // TODO: Remove line

        val myDataset: MutableList<CabinCustomerOrdersDetailContracts.Detailbox> = mutableListOf()

        for (i in 0..buttikCount) {
            for (j in 0..ordersCount) {
                myDataset.add(Orderbox())
            }
            myDataset.add(Footerbox())
        }

        val viewAdapter = CabinCustomerOrdersDetailAdapter(this, myDataset)
        val viewManager = LinearLayoutManager(this.context)

        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        pageView.findViewById<TextView>(R.id.orders_addressbar_order_type_label).text = resources.getText(R.string.pending_orders_label)
    }

    override fun setupShippingPage() {


        val buttikCount = 3 // TODO: Remove line
        val ordersCount = 4 // TODO: Remove line

        val myDataset: MutableList<CabinCustomerOrdersDetailContracts.Detailbox> = mutableListOf()

        for (i in 0..buttikCount) {
            myDataset.add(Cargobox())
            for (j in 0..ordersCount) {
                myDataset.add(Orderbox())
            }
            myDataset.add(Footerbox())
        }

        val viewAdapter = CabinCustomerOrdersDetailAdapter(this, myDataset)
        val viewManager = LinearLayoutManager(this.context)

        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        pageView.findViewById<TextView>(R.id.orders_addressbar_order_type_label).text = resources.getText(R.string.shipping_orders_label)
    }

    override fun setupSentPage() {


        val buttikCount = 4 // TODO: Remove line
        val ordersCount = 4 // TODO: Remove line

        val myDataset: MutableList<CabinCustomerOrdersDetailContracts.Detailbox> = mutableListOf()

        for (i in 0..buttikCount) {
            myDataset.add(Headerbox())
            for (j in 0..ordersCount) {
                myDataset.add(Orderbox())
            }
            myDataset.add(Footerbox())
        }

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
        (activity!! as com.cabinInformationTechnologies.cabin.MainActivity).showProgressBar()
    }

    override fun hideProgressBar() {
        (activity!! as com.cabinInformationTechnologies.cabin.MainActivity).hideProgressBar()
    }

    //endregion
}