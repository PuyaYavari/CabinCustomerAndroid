package com.cabinInformationTechnologies.cabin.fragments.filter

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.cabinInformationTechnologies.cabin.FilterTypeIDs
import com.cabinInformationTechnologies.cabin.MainActivity
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilter
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterCategory

class CabinCustomerFilterPresenter(var view: CabinCustomerFilterContracts.View?) :
    CabinCustomerFilterContracts.Presenter, CabinCustomerFilterContracts.InteractorOutput {

    var interactor: CabinCustomerFilterContracts.Interactor? = CabinCustomerFilterInteractor(this)
    var router: CabinCustomerFilterContracts.Router? = null
    override var filter: MODELFilter? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerFilterRouter(activity)

        filter = (activity as MainActivity).getFilter()

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
            FilterTypeIDs.SIZE -> {
                filter?.filterSizes?.forEach {
                    if (it.isSelected)
                        count++
                }
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

    //endregion

    //region InteractorOutput

    override fun refreshFilter(filter: MODELFilter) {
        this.filter = filter
        view?.setAmounts()
        view?.setupPage()
    }

    //endregion
}