package com.cabinInformationTechnologies.cabin.fragments.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.ViewPager
import com.cabinInformationTechnologies.cabin.MainActivity
import com.cabinInformationTechnologies.cabin.R


class CabinCustomerOrdersFragment : com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment(), CabinCustomerOrdersContracts.View {

    var presenter: CabinCustomerOrdersContracts.Presenter? = CabinCustomerOrdersPresenter(this)
    private lateinit var pageView: View
    private lateinit var mPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_orders, container, false)
        return pageView
    }

    override fun onResume() {
        super.onResume()

        (activity!! as MainActivity).setHeader(resources.getString(R.string.order_label),null)
        (activity!! as MainActivity).hideBackButton()
        (activity!! as MainActivity).lockDrawer()
        (activity!! as MainActivity).hideBackButton()
        (activity!! as MainActivity).hideClear()

        hideProgressBar()

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

    private fun setupPage () {
        mPager = pageView.findViewById(R.id.orders_pager)
        val pagerAdapter =
            CabinCustomerOrdersScreenSlidePagerAdapter(
                childFragmentManager, 0
            )
        mPager.adapter = pagerAdapter
    }

    override fun showProgressBar() {
        (activity!! as MainActivity).showProgressBar()
    }

    override fun hideProgressBar() {
        (activity!! as MainActivity).hideProgressBar()
    }

    //endregion
}
