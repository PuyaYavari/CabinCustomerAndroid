package com.cabinInformationTechnologies.cabin.fragments.filter

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilter

object CabinCustomerFilterContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        fun setupPage()

        fun showProgressBar()
        fun hideProgressBar()
        fun changeActivityFilter(filter: MODELFilter?)
        fun showCategoriesCountAs(count: Int)
        fun showSexesCountAs(count: Int)
        fun showSellersCountAs(count: Int)
        fun showSizesCountAs(count: Int)
        fun showColorsCountAs(count: Int)
        fun showPricesCountAs(count: Int)
        fun hideCategoriesCount()
        fun hideSexesCount()
        fun hideSellersCount()
        fun hideSizesCount()
        fun hideColorsCount()
        fun hidePricesCount()
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        var filter: MODELFilter?
        var previousFilter: MODELFilter?

        fun setAmounts()
        fun requestFilter(context: Context)
        fun moveToFilterDetail(filterType: Int)
        fun getSelectedCount(filterType: Int): Int
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {
        fun requestFilter(context: Context, filter: MODELFilter?)
    }

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput {
        fun refreshFilter(filter: MODELFilter)
    }

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router {
        fun moveToFilterDetail(filterType: Int)
    }

    interface FilterFragment {

    }

}