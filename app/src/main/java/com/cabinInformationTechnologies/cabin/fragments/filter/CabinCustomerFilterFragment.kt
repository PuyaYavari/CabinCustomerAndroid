package com.cabinInformationTechnologies.cabin.fragments.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.cabinInformationTechnologies.cabin.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class CabinCustomerFilterFragment : com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment(), CabinCustomerFilterContracts.View {

    var presenter: CabinCustomerFilterContracts.Presenter? = CabinCustomerFilterPresenter(this)
    private lateinit var pageView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_filter, container, false)
        val context = this.context
        if (context != null)
            presenter?.getFilter(context)
        setupActivityLayout()
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
        val context = this.context
        pageView.findViewById<ConstraintLayout>(R.id.filter_color_layout).setOnClickListener {
            presenter?.moveToFilterDetail(com.cabinInformationTechnologies.cabin.FilterTypeIDs.COLOR)
        }
    }

    private fun setupActivityLayout() {
        if ((activity!! as com.cabinInformationTechnologies.cabin.MainActivity).findViewById<ConstraintLayout>(R.id.main_header).translationY != 0f &&
            (activity!! as com.cabinInformationTechnologies.cabin.MainActivity).findViewById<BottomNavigationView>(R.id.navbar).translationY != 0f) {
            (activity!! as com.cabinInformationTechnologies.cabin.MainActivity).hideNavbarFromHidden()
        } else if ((activity!! as com.cabinInformationTechnologies.cabin.MainActivity).findViewById<ConstraintLayout>(R.id.main_header).translationY == 0f &&
            (activity!! as com.cabinInformationTechnologies.cabin.MainActivity).findViewById<BottomNavigationView>(R.id.navbar).translationY == 0f) {
            (activity!! as com.cabinInformationTechnologies.cabin.MainActivity).hideNavbarFromDefault()
        }
        (activity!! as com.cabinInformationTechnologies.cabin.MainActivity).lockDrawer()
        hideProgressBar()
    }

    override fun setFilter(filter: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilter) {
        (activity!! as com.cabinInformationTechnologies.cabin.MainActivity).setFilter(filter)
    }

    override fun showProgressBar() {
        (activity!! as com.cabinInformationTechnologies.cabin.MainActivity).showProgressBar()
    }

    override fun hideProgressBar() {
        (activity!! as com.cabinInformationTechnologies.cabin.MainActivity).hideProgressBar()
    }

    //endregion
}