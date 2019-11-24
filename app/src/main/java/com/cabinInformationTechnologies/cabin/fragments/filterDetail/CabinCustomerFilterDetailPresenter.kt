package com.cabinInformationTechnologies.cabin.fragments.filterDetail

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.cabinInformationTechnologies.cabin.FilterTypeIDs
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilter
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterCategory
import com.cabinInformationTechnologies.cabinCustomerBase.ThreeStateSelection

class CabinCustomerFilterDetailPresenter(var view: CabinCustomerFilterDetailContracts.View?) :
    CabinCustomerFilterDetailContracts.Presenter,
    CabinCustomerFilterDetailContracts.InteractorOutput {

    var interactor: CabinCustomerFilterDetailContracts.Interactor? =
        CabinCustomerFilterDetailInteractor(this)
    var router: CabinCustomerFilterDetailContracts.Router? = null

    override var filter: MODELFilter? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerFilterDetailRouter(activity)
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

    override fun setupPage(filterType: Int) {
        if (filter != null)
            when (filterType) {
                FilterTypeIDs.CATEGORY -> {
                    val dataset = filter?.filterCategories
                    if (!dataset.isNullOrEmpty())
                        view?.setupCategoriesPage(dataset)
                }
                FilterTypeIDs.SEX -> {
                    val dataset = filter?.sexes
                    if (!dataset.isNullOrEmpty())
                        view?.setupSexesPage(dataset)
                }
                FilterTypeIDs.SELLER -> {
                    val dataset = filter?.sellers
                    if (!dataset.isNullOrEmpty())
                        view?.setupSellersPage(dataset)
                }
                FilterTypeIDs.SIZE -> {
                    val dataset = filter?.filterSizes
                    if (!dataset.isNullOrEmpty())
                        view?.setupSizesPage(dataset)
                }
                FilterTypeIDs.COLOR -> {
                    val myColorsDataset = filter?.colors
                    if (!myColorsDataset.isNullOrEmpty())
                        view?.setupColorsPage(myColorsDataset)
                }
                FilterTypeIDs.PRICE -> {
                    val dataset = filter?.filterPrices
                    if (!dataset.isNullOrEmpty())
                        view?.setupPricesPage(dataset)
                }
            }
    }

    override fun clearFilter(filterType: Int) {
        when (filterType) {
            FilterTypeIDs.CATEGORY -> {
                var categories = filter?.filterCategories
                if (categories != null)
                    filter?.filterCategories = unselectAllCategories(categories)
                categories = filter?.filterCategories
                Log.d("categories size", "${categories?.size}")
                if (categories != null)
                    view?.changeCategoriesDataset(categories)
            }
            FilterTypeIDs.SEX -> {
                val sexes = filter?.sexes
                sexes?.forEach {
                    it.isSelected = false
                }
                if (sexes != null)
                    view?.changeSexesDataset(sexes)
            }
            FilterTypeIDs.SELLER -> {
                val sellers = filter?.sellers
                sellers?.forEach {
                    it.isSelected = false
                }
                if (sellers != null)
                    view?.changeSellersDataset(sellers)
            }
            FilterTypeIDs.SIZE -> {
                val sizegroups = filter?.filterSizes
                sizegroups?.forEach {sizeGroup ->
                    sizeGroup.isSelected = false
                    sizeGroup.getSizes()?.forEach {size ->
                        size.isSelected = false
                    }
                }
                if (sizegroups != null)
                    view?.changeSizesDataset(sizegroups)
            }
            FilterTypeIDs.COLOR -> {
                val colors = filter?.colors
                colors?.forEach {
                    it.isSelected = false
                }
                if (colors != null)
                    view?.changeColorsDataset(colors)
            }
            FilterTypeIDs.PRICE -> {
                val prices = filter?.filterPrices
                prices?.forEach {
                    it.isSelected = false
                }
                if (prices != null)
                    view?.changePricesDataset(prices)
            }
        }
    }

    private fun unselectAllCategories(categories: MutableList<MODELFilterCategory>): MutableList<MODELFilterCategory> {
        categories.forEach {
            if (!it.getSubCategories().isNullOrEmpty()) {
                val subCategories = it.getSubCategories()
                if (subCategories != null)
                    unselectAllCategories(subCategories)
                it.state = ThreeStateSelection.UNSELECTED
            } else {
                it.isSelected = false
            }
        }
        return categories
    }

    //endregion

    //region InteractorOutput

    //TODO: Implement your InteractorOutput methods here

    //endregion
}