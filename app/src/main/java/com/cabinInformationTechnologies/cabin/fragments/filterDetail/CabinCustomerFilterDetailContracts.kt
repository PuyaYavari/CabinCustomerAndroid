package com.cabinInformationTechnologies.cabin.fragments.filterDetail

import com.cabinInformationTechnologies.cabinCustomerBase.models.local.*

object CabinCustomerFilterDetailContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        fun setupCategoriesPage(dataset: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterCategory>)
        fun setupSexesPage(dataset: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterSex>)
        fun setupSellersPage(dataset: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterSeller>)
        fun setupSizesPage(dataset: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterSizeGroup>)
        fun setupColorsPage(dataset: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterColor>)
        fun setupPricesPage(dataset: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterPrice>)
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        fun setupPage(
            filterType: Int,
            filter: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilter?
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