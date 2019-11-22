package com.cabinInformationTechnologies.cabin.fragments.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import com.cabinInformationTechnologies.cabin.FilterTypeIDs
import com.cabinInformationTechnologies.cabin.MainActivity
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilter
import com.google.android.material.bottomnavigation.BottomNavigationView

class CabinCustomerFilterFragment : com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment(),
    CabinCustomerFilterContracts.View, CabinCustomerFilterContracts.FilterFragment {

    var presenter: CabinCustomerFilterContracts.Presenter? = CabinCustomerFilterPresenter(this)
    private lateinit var pageView: View

    private lateinit var callback: OnBackPressedCallback

    private var previousFilter: MODELFilter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        previousFilter = (activity as MainActivity).getFilter()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_filter, container, false)
        presenter?.filter = (activity as MainActivity).getFilter()
        val context = this.context
        if (context != null)
            presenter?.requestFilter(context)
        setupActivityLayout()

        (activity as MainActivity).hideDrawerButton()
        (activity as MainActivity).showCrossOfFilter(previousFilter)

        // This callback will only be called when MyFragment is at least Started.
        callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            (activity as MainActivity).setFilterTo(previousFilter)
            activity?.findNavController(R.id.nav_host_fragment)?.popBackStack()
        }

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

    override fun setupPage() {
        pageView.findViewById<ConstraintLayout>(R.id.filter_categories_layout).setOnClickListener {
            presenter?.moveToFilterDetail(FilterTypeIDs.CATEGORY)
        }
        pageView.findViewById<ConstraintLayout>(R.id.filter_sex_layout).setOnClickListener {
            presenter?.moveToFilterDetail(FilterTypeIDs.SEX)
        }
        pageView.findViewById<ConstraintLayout>(R.id.filter_brand_layout).setOnClickListener {
            presenter?.moveToFilterDetail(FilterTypeIDs.SELLER)
        }
        pageView.findViewById<ConstraintLayout>(R.id.filter_size_layout).setOnClickListener {
            presenter?.moveToFilterDetail(FilterTypeIDs.SIZE)
        }
        pageView.findViewById<ConstraintLayout>(R.id.filter_color_layout).setOnClickListener {
            presenter?.moveToFilterDetail(FilterTypeIDs.COLOR)
        }
        pageView.findViewById<ConstraintLayout>(R.id.filter_price_layout).setOnClickListener {
            presenter?.moveToFilterDetail(FilterTypeIDs.PRICE)
        }
        pageView.findViewById<Button>(R.id.filter_footer_confirm_button).setOnClickListener {
            activity?.findNavController(R.id.nav_host_fragment)?.popBackStack()
        }
    }

    override fun unsetPage() {
        pageView.findViewById<ConstraintLayout>(R.id.filter_categories_layout).setOnClickListener {}
        pageView.findViewById<ConstraintLayout>(R.id.filter_sex_layout).setOnClickListener {}
        pageView.findViewById<ConstraintLayout>(R.id.filter_brand_layout).setOnClickListener {}
        pageView.findViewById<ConstraintLayout>(R.id.filter_size_layout).setOnClickListener {}
        pageView.findViewById<ConstraintLayout>(R.id.filter_color_layout).setOnClickListener {}
        pageView.findViewById<ConstraintLayout>(R.id.filter_price_layout).setOnClickListener {}
        pageView.findViewById<Button>(R.id.filter_footer_confirm_button).setOnClickListener {}
        hideCategoriesCount()
        hideSexesCount()
        hideSellersCount()
        hideSizesCount()
        hideColorsCount()
        hidePricesCount()
    }

    override fun showCategoriesCountAs(count: Int) {
        pageView.apply {
            findViewById<TextView>(R.id.filter_category_count).text = count.toString()
            findViewById<LinearLayout>(R.id.filter_category_count_layout).visibility = View.VISIBLE
        }
    }

    override fun hideCategoriesCount() {
        pageView.apply {
            findViewById<TextView>(R.id.filter_category_count).text = ""
            findViewById<LinearLayout>(R.id.filter_category_count_layout).visibility = View.INVISIBLE
        }
    }

    override fun showSexesCountAs(count: Int) {
        pageView.apply {
            findViewById<TextView>(R.id.filter_sex_count).text = count.toString()
            findViewById<LinearLayout>(R.id.filter_sex_count_layout).visibility = View.VISIBLE
        }
    }

    override fun hideSexesCount() {
        pageView.apply {
            findViewById<TextView>(R.id.filter_sex_count).text = ""
            findViewById<LinearLayout>(R.id.filter_sex_count_layout).visibility = View.INVISIBLE
        }
    }

    override fun showSellersCountAs(count: Int) {
        pageView.apply {
            findViewById<TextView>(R.id.filter_brand_count).text = count.toString()
            findViewById<LinearLayout>(R.id.filter_brand_count_layout).visibility = View.VISIBLE
        }
    }

    override fun hideSellersCount() {
        pageView.apply {
            findViewById<TextView>(R.id.filter_brand_count).text = ""
            findViewById<LinearLayout>(R.id.filter_brand_count_layout).visibility = View.INVISIBLE
        }
    }

    override fun showSizesCountAs(count: Int) {
        pageView.apply {
            findViewById<TextView>(R.id.filter_size_count).text = count.toString()
            findViewById<LinearLayout>(R.id.filter_size_count_layout).visibility = View.VISIBLE
        }
    }

    override fun hideSizesCount() {
        pageView.apply {
            findViewById<TextView>(R.id.filter_size_count).text = ""
            findViewById<LinearLayout>(R.id.filter_size_count_layout).visibility = View.INVISIBLE
        }
    }

    override fun showColorsCountAs(count: Int) {
        pageView.apply {
            findViewById<TextView>(R.id.filter_color_count).text = count.toString()
            findViewById<LinearLayout>(R.id.filter_color_count_layout).visibility = View.VISIBLE
        }
    }

    override fun hideColorsCount() {
        pageView.apply {
            findViewById<TextView>(R.id.filter_color_count).text = ""
            findViewById<LinearLayout>(R.id.filter_color_count_layout).visibility = View.INVISIBLE
        }
    }

    override fun showPricesCountAs(count: Int) {
        pageView.apply {
            findViewById<TextView>(R.id.filter_price_count).text = count.toString()
            findViewById<LinearLayout>(R.id.filter_price_count_layout).visibility = View.VISIBLE
        }
    }

    override fun hidePricesCount() {
        pageView.apply {
            findViewById<TextView>(R.id.filter_price_count).text = ""
            findViewById<LinearLayout>(R.id.filter_price_count_layout).visibility = View.INVISIBLE
        }
    }

    override fun showProgressBar() {
        (activity!! as MainActivity).showProgressBar()
    }

    override fun hideProgressBar() {
        (activity!! as MainActivity).hideProgressBar()
    }

    override fun changeActivityFilter(filter: MODELFilter?) {
        (activity!! as MainActivity).setFilter(filter)
    }

    override fun clearFilter() {
        val context =  this.context
        if (context != null)
            presenter?.clearFilter(context)
    }

    private fun setupActivityLayout() {
        if ((activity!! as MainActivity).findViewById<ConstraintLayout>(R.id.main_header).translationY != 0f &&
            (activity!! as MainActivity).findViewById<BottomNavigationView>(R.id.navbar).translationY != 0f) {
            (activity!! as MainActivity).hideNavbarFromHidden()
        } else if ((activity!! as MainActivity).findViewById<ConstraintLayout>(R.id.main_header).translationY == 0f &&
            (activity!! as MainActivity).findViewById<BottomNavigationView>(R.id.navbar).translationY == 0f) {
            (activity!! as MainActivity).hideNavbarFromDefault()
        }
        (activity!! as MainActivity).lockDrawer()
        (activity!! as MainActivity).showClear(this)
        hideProgressBar()
    }
    //endregion
}