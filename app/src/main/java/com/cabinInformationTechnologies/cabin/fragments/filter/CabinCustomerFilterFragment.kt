package com.cabinInformationTechnologies.cabin.fragments.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.cabinInformationTechnologies.cabin.FilterTypeIDs
import com.cabinInformationTechnologies.cabin.MainActivity
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
            presenter?.requestFilter(context)
        setupActivityLayout()
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
            changeActivityFilter()
            activity?.onBackPressed()
        }
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
        hideProgressBar()
    }

    override fun setAmounts() {
        val filter = presenter?.filter
        if (filter != null)
            pageView.apply {
                var selectedCount: Int? = presenter?.getSelectedCount(FilterTypeIDs.CATEGORY)
                if (selectedCount != null && selectedCount > 0) {
                    findViewById<TextView>(R.id.filter_category_count).text = selectedCount.toString()
                    findViewById<LinearLayout>(R.id.filter_category_count_layout).visibility = View.VISIBLE
                } else {
                    findViewById<LinearLayout>(R.id.filter_category_count_layout).visibility = View.INVISIBLE
                }

                selectedCount = presenter?.getSelectedCount(FilterTypeIDs.SEX)
                if (selectedCount != null && selectedCount > 0) {
                    findViewById<TextView>(R.id.filter_sex_count).text = selectedCount.toString()
                    findViewById<LinearLayout>(R.id.filter_sex_count_layout).visibility = View.VISIBLE
                } else {
                    findViewById<LinearLayout>(R.id.filter_sex_count_layout).visibility = View.INVISIBLE
                }

                selectedCount = presenter?.getSelectedCount(FilterTypeIDs.SELLER)
                if (selectedCount != null && selectedCount > 0) {
                    findViewById<TextView>(R.id.filter_brand_count).text = selectedCount.toString()
                    findViewById<LinearLayout>(R.id.filter_brand_count_layout).visibility = View.VISIBLE
                } else {
                    findViewById<LinearLayout>(R.id.filter_brand_count_layout).visibility = View.INVISIBLE
                }

                selectedCount = presenter?.getSelectedCount(FilterTypeIDs.SIZE)
                if (selectedCount != null && selectedCount > 0) {
                    findViewById<TextView>(R.id.filter_size_count).text = selectedCount.toString()
                    findViewById<LinearLayout>(R.id.filter_size_count_layout).visibility = View.VISIBLE
                } else {
                    findViewById<LinearLayout>(R.id.filter_size_count_layout).visibility = View.INVISIBLE
                }

                selectedCount = presenter?.getSelectedCount(FilterTypeIDs.COLOR)
                if (selectedCount != null && selectedCount > 0) {
                    findViewById<TextView>(R.id.filter_color_count).text = selectedCount.toString()
                    findViewById<LinearLayout>(R.id.filter_color_count_layout).visibility = View.VISIBLE
                } else {
                    findViewById<LinearLayout>(R.id.filter_color_count_layout).visibility = View.INVISIBLE
                }

                selectedCount = presenter?.getSelectedCount(FilterTypeIDs.PRICE)
                if (selectedCount != null && selectedCount > 0) {
                    findViewById<TextView>(R.id.filter_price_count).text = selectedCount.toString()
                    findViewById<LinearLayout>(R.id.filter_price_count_layout).visibility = View.VISIBLE
                } else {
                    findViewById<LinearLayout>(R.id.filter_price_count_layout).visibility = View.INVISIBLE
                }
            }
    }

    override fun showProgressBar() {
        (activity!! as MainActivity).showProgressBar()
    }

    override fun hideProgressBar() {
        (activity!! as MainActivity).hideProgressBar()
    }

    override fun changeActivityFilter() {
        (activity!! as MainActivity).setFilter(presenter?.filter)
    }

    //endregion
}