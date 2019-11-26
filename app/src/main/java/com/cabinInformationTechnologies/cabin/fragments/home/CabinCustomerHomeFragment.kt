package com.cabinInformationTechnologies.cabin.fragments.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.MainActivity
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.GlobalData

class CabinCustomerHomeFragment : com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment(), CabinCustomerHomeContracts.View {

    var presenter: CabinCustomerHomeContracts.Presenter? = CabinCustomerHomePresenter(this)
    private lateinit var pageView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_home, container, false)

        GlobalData.appRunning = true

        val mainBanner = pageView.findViewById<ImageView>(R.id.home_main_banner)
        val mainBannerParams = mainBanner.layoutParams
        var displayWidth = -1
        val displayMetrics = context?.resources?.displayMetrics
        if(displayMetrics != null) {
            displayWidth = displayMetrics.widthPixels
        }
        mainBannerParams.width = displayWidth
        mainBannerParams.height = displayWidth * 4/3 //FIXME: FIND BEST RATIO
        mainBanner.layoutParams = mainBannerParams

        presenter?.requestHeaders(this.context)

        setupActivity()
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

    private fun setupActivity() {
        (activity!! as MainActivity).unblockPage()
        (activity!! as MainActivity).setHeader(resources.getString(R.string.homepage_label),null)
        (activity!! as MainActivity).hideBackButton()
        (activity!! as MainActivity).showHeaderNavbar()
        (activity!! as MainActivity).unlockDrawer()
        (activity!! as MainActivity).showDrawerButton()
        (activity!! as MainActivity).hideBackButton()
        (activity!! as MainActivity).hideClear()
        (activity!! as MainActivity).hideCross()
        (activity!! as MainActivity).hideProgressBar()
        (activity!! as MainActivity).setHeader("", null)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            (activity!! as MainActivity).setHeaderColor(null as Int?)
        } else {
            (activity!! as MainActivity).setHeaderColor(null as Int?)
        }
    }

    private fun setupPage() {
        (activity!! as MainActivity).getRecyclerView().apply {
            visibility = View.VISIBLE
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = presenter?.headerAdapter
        }

        //TODO: SETUP MAIN BANNER
        pageView.findViewById<RecyclerView>(R.id.home_recycler_view).apply {
            setHasFixedSize(false)
            layoutManager = object : LinearLayoutManager(this.context, VERTICAL, false) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            adapter = presenter?.homeAdapter
        }
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