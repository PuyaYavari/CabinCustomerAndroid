package com.cabinInformationTechnologies.cabin.fragments.filterDetail

import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.*

object CabinCustomerFilterDetailContracts {

    interface View : BaseContracts.View {
        fun setupCategoriesPage(dataset: MutableList<MODELFilterCategory>)
        fun setupSexesPage(dataset: MutableList<MODELFilterSex>)
        fun setupSellersPage(dataset: MutableList<MODELFilterSeller>)
        fun setupSizesPage(dataset: MutableList<MODELFilterSizeGroup>)
        fun setupColorsPage(dataset: MutableList<MODELFilterColor>)
        fun setupPricesPage(dataset: MutableList<MODELFilterPrice>)
        fun changeCategoriesDataset(dataset: MutableList<MODELFilterCategory>)
        fun changeSexesDataset(dataset: MutableList<MODELFilterSex>)
        fun changeSellersDataset(dataset: MutableList<MODELFilterSeller>)
        fun changeSizesDataset(dataset: MutableList<MODELFilterSizeGroup>)
        fun changeColorsDataset(dataset: MutableList<MODELFilterColor>)
        fun changePricesDataset(dataset: MutableList<MODELFilterPrice>)
    }

    interface Presenter : BaseContracts.Presenter {
        var filter: MODELFilter?

        fun setupPage(filterType: Int)
        fun clearFilter(filterType: Int)
    }

    interface Interactor : BaseContracts.Interactor

    interface InteractorOutput : BaseContracts.InteractorOutput

    interface Router : BaseContracts.Router

    interface SizesCallback {
        fun setSizes(sizes: List<MODELFilterSize>)
    }

    interface CategoryCallback {
        fun setSubCat(dataset: MutableList<MODELFilterCategory>)
    }

    interface FilterDetailFragment {
        fun clearFilter()
    }
}