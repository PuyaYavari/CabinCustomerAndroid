package com.cabinInformationTechnologies.cabin.fragments.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.cabinInformationTechnologies.cabin.FilterTypeIDs
import com.cabinInformationTechnologies.cabin.MainActivity
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilter
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterCategory
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

    private fun setAmounts() {
        val filter = (activity!! as MainActivity).getFilter()
        if (filter != null)
            pageView.apply {
                var selectedCount: Int = getSelectedCount(filter, FilterTypeIDs.CATEGORY)
                if (selectedCount > 0) {
                    findViewById<TextView>(R.id.filter_category_count).text = selectedCount.toString()
                    findViewById<LinearLayout>(R.id.filter_category_count_layout).visibility = View.VISIBLE
                } else {
                    findViewById<LinearLayout>(R.id.filter_category_count_layout).visibility = View.INVISIBLE
                }

                selectedCount = getSelectedCount(filter, FilterTypeIDs.SEX)
                if (selectedCount > 0) {
                    findViewById<TextView>(R.id.filter_sex_count).text = selectedCount.toString()
                    findViewById<LinearLayout>(R.id.filter_sex_count_layout).visibility = View.VISIBLE
                } else {
                    findViewById<LinearLayout>(R.id.filter_sex_count_layout).visibility = View.INVISIBLE
                }

                selectedCount = getSelectedCount(filter, FilterTypeIDs.SELLER)
                if (selectedCount > 0) {
                    findViewById<TextView>(R.id.filter_brand_count).text = selectedCount.toString()
                    findViewById<LinearLayout>(R.id.filter_brand_count_layout).visibility = View.VISIBLE
                } else {
                    findViewById<LinearLayout>(R.id.filter_brand_count_layout).visibility = View.INVISIBLE
                }

                selectedCount = getSelectedCount(filter, FilterTypeIDs.SIZE)
                if (selectedCount > 0) {
                    findViewById<TextView>(R.id.filter_size_count).text = selectedCount.toString()
                    findViewById<LinearLayout>(R.id.filter_size_count_layout).visibility = View.VISIBLE
                } else {
                    findViewById<LinearLayout>(R.id.filter_size_count_layout).visibility = View.INVISIBLE
                }

                selectedCount = getSelectedCount(filter, FilterTypeIDs.COLOR)
                if (selectedCount > 0) {
                    findViewById<TextView>(R.id.filter_color_count).text = selectedCount.toString()
                    findViewById<LinearLayout>(R.id.filter_color_count_layout).visibility = View.VISIBLE
                } else {
                    findViewById<LinearLayout>(R.id.filter_color_count_layout).visibility = View.INVISIBLE
                }

                selectedCount = getSelectedCount(filter, FilterTypeIDs.PRICE)
                if (selectedCount > 0) {
                    findViewById<TextView>(R.id.filter_price_count).text = selectedCount.toString()
                    findViewById<LinearLayout>(R.id.filter_price_count_layout).visibility = View.VISIBLE
                } else {
                    findViewById<LinearLayout>(R.id.filter_price_count_layout).visibility = View.INVISIBLE
                }
            }
    }

    private fun getSelectedCount(filter: MODELFilter, filterType: Int): Int {
        var count = 0
        when (filterType) {
            FilterTypeIDs.CATEGORY -> {
                //TODO: SELECTED CATEGORIES COUNT
            }
            FilterTypeIDs.SEX -> {
                filter.sexes?.forEach {
                    if (it.getIsSelected())
                        count++
                }
            }
            FilterTypeIDs.SELLER -> {
                filter.sellers?.forEach {
                    if (it.isSelected)
                        count++
                }
            }
            FilterTypeIDs.SIZE -> {
                filter.filterSizes?.forEach {
                    if (it.getIsSelected())
                        count++
                }
            }
            FilterTypeIDs.COLOR -> {
                filter.colors?.forEach {
                    if (it.getIsSelected())
                        count++
                }
            }
            FilterTypeIDs.PRICE -> {
                filter.filterPrices?.forEach {
                    if (it.getIsSelected())
                        count++
                }
            }
        }
        return count
    }

    private fun countSelectedCategories(category: MutableList<MODELFilterCategory?>): Int {
        var newCount = 0
        category.forEach { baseCategory ->
            val hasSubCat = baseCategory?.getSubCategories()?.isNotEmpty()
            if (hasSubCat != null && hasSubCat) {
                val subCategories = baseCategory.getSubCategories()
                if (!subCategories.isNullOrEmpty())
                    newCount += countSelectedCategories(subCategories)
            } else {
                val isSelected = baseCategory?.getIsSelected()
                if (isSelected != null && isSelected)
                    newCount++
            }
        }
        return newCount
    }

    override fun setFilter(filter: MODELFilter) {
        (activity!! as MainActivity).setFilter(filter)
        setAmounts()
    }

    override fun showProgressBar() {
        (activity!! as MainActivity).showProgressBar()
    }

    override fun hideProgressBar() {
        (activity!! as MainActivity).hideProgressBar()
    }

    //endregion
}