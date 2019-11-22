package com.cabinInformationTechnologies.cabin.fragments.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.ViewPager
import com.cabinInformationTechnologies.cabin.MainActivity
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.GlobalData


class CabinCustomerOrdersFragment :
    com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment(),
    CabinCustomerOrdersContracts.View {

    var presenter: CabinCustomerOrdersContracts.Presenter? = CabinCustomerOrdersPresenter(this)
    private lateinit var pageView: View
    private var mPager: ViewPager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_orders, container, false)
        return pageView
    }

    override fun onResume() {
        super.onResume()

        setupActivity()
        setupPage()

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
        (activity!! as MainActivity).setHeader(resources.getString(R.string.order_label),null)
        (activity!! as MainActivity).hideBackButton()
        (activity!! as MainActivity).hideDrawerButton()
        (activity!! as MainActivity).lockDrawer()
        (activity!! as MainActivity).hideBackButton()
        (activity!! as MainActivity).hideClear()
        (activity!! as MainActivity).hideCross()
        (activity!! as MainActivity).hideProgressBar()

    }

    override fun setupPage () {
        if (GlobalData.loggedIn) {
            val context = this.context
            if (context != null && (presenter as CabinCustomerOrdersContracts.FragmentsManager)
                    .currentPage == 0)
                (presenter as CabinCustomerOrdersContracts.FragmentsManager).getFirstPage(context)
            else {
                mPager = pageView.findViewById(R.id.orders_pager)

                val pagerAdapter =
                    CabinCustomerOrdersScreenSlidePagerAdapter(
                        childFragmentManager,
                        0,
                        presenter as CabinCustomerOrdersContracts.FragmentsManager
                    )
                mPager?.adapter = pagerAdapter
            }
            if ((activity!! as MainActivity).findViewById<ConstraintLayout>(R.id.blocker_layout)
                    .visibility == View.INVISIBLE) {
                (activity!! as MainActivity).layoutBackToDefault()
                (activity!! as MainActivity).showHeaderNavbar()
            } else
                (activity!! as MainActivity).unblockPage()
        } else
            (activity!! as MainActivity).showNeedLogin()

    }

    override fun showProgressBar() {
        (activity!! as MainActivity).showProgressBar()
    }

    override fun hideProgressBar() {
        (activity!! as MainActivity).hideProgressBar()
    }

    override fun showNoInternet() {
        hideProgressBar()
        pageView.findViewById<ConstraintLayout>(R.id.orders_no_internet_layout).visibility = View.VISIBLE
        pageView.findViewById<Button>(R.id.orders_no_internet_button).setOnClickListener { setupPage() }
    }

    override fun hideNoInternet() {
        pageView.findViewById<ConstraintLayout>(R.id.orders_no_internet_layout).visibility = View.INVISIBLE
    }

    //endregion
}
