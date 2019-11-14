package com.cabinInformationTechnologies.cabin.fragments.filter

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.cabinInformationTechnologies.cabin.FilterTypeIDs
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilter
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterCategory
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterSizeGroup

class CabinCustomerFilterPresenter(var view: CabinCustomerFilterContracts.View?) :
    CabinCustomerFilterContracts.Presenter, CabinCustomerFilterContracts.InteractorOutput {

    var interactor: CabinCustomerFilterContracts.Interactor? = CabinCustomerFilterInteractor(this)
    var router: CabinCustomerFilterContracts.Router? = null
    override var filter: MODELFilter? = null
        set(value) {
            field = value
            view?.changeActivityFilter(value)
        }
    override var previousFilter: MODELFilter? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerFilterRouter(activity)
    }

    override fun onDestroy() {
        view = null
        interactor?.unregister()
        interactor = null
        router?.unregister()
        router = null
    }

    //endregion

    //region Presenter

    private fun countSelectedCategories(category: MutableList<MODELFilterCategory>?): Int {
        var newCount = 0
        category?.forEach { baseCategory ->
            val hasSubCat = baseCategory.getSubCategories()?.isNotEmpty()
            if (hasSubCat != null && hasSubCat) {
                val subCategories = baseCategory.getSubCategories()
                if (!subCategories.isNullOrEmpty())
                    newCount += countSelectedCategories(subCategories)
            } else {
                val isSelected = baseCategory.isSelected
                if (isSelected != null && isSelected)
                    newCount++
            }
        }
        return newCount
    }

    private fun countSelectedSizes(sizes: MutableList<MODELFilterSizeGroup>?): Int {
        var newCount =  0
        sizes?.forEach { sizeGroup ->
            sizeGroup.getSizes()?.forEach { size ->
                if (size.isSelected)
                    newCount++
            }
        }
        return newCount
    }

    override fun requestFilter(context: Context) {
        interactor?.requestFilter(context, filter)
    }

    override fun moveToFilterDetail(filterType: Int) {
        router?.moveToFilterDetail(filterType)
    }

    override fun getSelectedCount(filterType: Int): Int {
        var count = 0
        when (filterType) {
            FilterTypeIDs.CATEGORY -> {
                count = countSelectedCategories(filter?.filterCategories)
            }
            FilterTypeIDs.SEX -> {
                filter?.sexes?.forEach {
                    if (it.isSelected)
                        count++
                }
            }
            FilterTypeIDs.SELLER -> {
                filter?.sellers?.forEach {
                    if (it.isSelected)
                        count++
                }
            }
            FilterTypeIDs.SIZE -> { //FIXME
                count = countSelectedSizes(filter?.filterSizes)
            }
            FilterTypeIDs.COLOR -> {
                filter?.colors?.forEach {
                    if (it.isSelected)
                        count++
                }
            }
            FilterTypeIDs.PRICE -> {
                filter?.filterPrices?.forEach {
                    if (it.isSelected)
                        count++
                }
            }
        }
        return count
    }

    override fun setAmounts() {
        val filter = this.filter
        if (filter != null) {

            var selectedCount: Int? = getSelectedCount(FilterTypeIDs.CATEGORY)
            if (selectedCount != null && selectedCount > 0) {
                view?.showCategoriesCountAs(selectedCount)
            } else {
                view?.hideCategoriesCount()
            }

            selectedCount = getSelectedCount(FilterTypeIDs.SEX)
            if (selectedCount > 0) {
                view?.showSexesCountAs(selectedCount)
            } else {
                view?.hideSexesCount()
            }

            selectedCount = getSelectedCount(FilterTypeIDs.SELLER)
            if (selectedCount > 0) {
                view?.showSellersCountAs(selectedCount)
            } else {
                view?.hideSellersCount()
            }

            selectedCount = getSelectedCount(FilterTypeIDs.SIZE)
            if (selectedCount > 0) {
                view?.showSizesCountAs(selectedCount)
            } else {
                view?.hideSizesCount()
            }

            selectedCount = getSelectedCount(FilterTypeIDs.COLOR)
            if (selectedCount > 0) {
                view?.showColorsCountAs(selectedCount)
            } else {
                view?.hideColorsCount()
            }

            selectedCount = getSelectedCount(FilterTypeIDs.PRICE)
            if (selectedCount > 0) {
                view?.showPricesCountAs(selectedCount)
            } else {
                view?.hidePricesCount()
            }
        }
    }

    override fun clearFilter(context: Context) {
        if (filter != null && !isFilterAllUnselected()) {
            view?.unsetPage()
            interactor?.clearFilter(context)
        }
    }

    private fun isFilterAllUnselected (): Boolean {
        val allSelectedCount =
            getSelectedCount(FilterTypeIDs.CATEGORY)                +
                getSelectedCount(FilterTypeIDs.SEX)                 +
                    getSelectedCount(FilterTypeIDs.SELLER)          +
                        getSelectedCount(FilterTypeIDs.SIZE)        +
                            getSelectedCount(FilterTypeIDs.COLOR)   +
                                getSelectedCount(FilterTypeIDs.PRICE)
        if (allSelectedCount > 0)
            return false
        return true
    }

    //endregion

    //region InteractorOutput

    override fun refreshFilter(filter: MODELFilter) {
        this.filter = filter
        setAmounts()
        view?.setupPage()
    }

    override fun failedToClearFilter(message: String?) {
        if (message != null)
            Toast.makeText(
                view?.getActivityContext(),
                message,
                Toast.LENGTH_SHORT
            ).show()
        else
            Toast.makeText(
                view?.getActivityContext(),
                view?.getActivityContext()?.resources?.getString(R.string.default_error_message),
                Toast.LENGTH_SHORT
            ).show()
        //TODO BETTER FEEDBACK
        val lastFilter = this.filter
        if (lastFilter != null)
            refreshFilter(lastFilter)
    }

    //endregion
}