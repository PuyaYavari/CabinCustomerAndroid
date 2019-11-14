package com.cabinInformationTechnologies.cabin.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cabinInformationTechnologies.cabin.MainActivity
import com.cabinInformationTechnologies.cabin.R

class CabinCustomerHomeFragment : com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment(), CabinCustomerHomeContracts.View {

    var presenter: CabinCustomerHomeContracts.Presenter? = CabinCustomerHomePresenter(this)
    private lateinit var pageView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_home, container, false)

        com.cabinInformationTechnologies.cabinCustomerBase.GlobalData.appRunning = true

        (activity!! as MainActivity).unblockPage()
        (activity!! as MainActivity).setHeader(resources.getString(R.string.homepage_label),null)
        (activity!! as MainActivity).hideBackButton()
        (activity!! as MainActivity).showHeaderNavbar()
        (activity!! as MainActivity).unlockDrawer()
        (activity!! as MainActivity).hideBackButton()
        (activity!! as MainActivity).hideClear()
        (activity!! as MainActivity).hideCross()
        hideProgressBar()

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
    }

    override fun showProgressBar() {
        (activity!! as MainActivity).showProgressBar()
    }

    override fun hideProgressBar() {
        (activity!! as MainActivity).hideProgressBar()
    }

    override fun lockDrawer() {
        (activity!! as MainActivity).lockDrawer()
    }

    override fun unlockDrawer() {
        (activity!! as MainActivity).unlockDrawer()
    }

    //endregion
}