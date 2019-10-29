package com.cabinInformationTechnologies.cabin.fragments.filterDetail

import com.cabinInformationTechnologies.cabinCustomerBase.models.local.*

object CabinCustomerFilterDetailContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        fun setupCategoriesPage(dataset: MutableList<MODELFilterCategory>)
        fun setupSexesPage(dataset: MutableList<MODELFilterSex>)
        fun setupSellersPage(dataset: MutableList<MODELFilterSeller>)
        fun setupSizesPage(dataset: MutableList<MODELFilterSize>)
        fun setupColorsPage(dataset: MutableList<MODELFilterColor>)
        fun setupPricesPage(dataset: MutableList<MODELFilterPrice>)
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        fun setupPage(
            filterType: Int,
            filter: MODELFilter?
        )
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {
        //TODO
    }

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router {
        //TODO
    }

}