package com.cabinInformationTechnologies.cabin.fragments.filter

import android.content.Context

object CabinCustomerFilterContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        fun showProgressBar()
        fun hideProgressBar()
        fun setFilter(filter: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilter)
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        fun getFilter(context: Context?)
        fun moveToFilterDetail(filterType: Int)
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {
        fun getFilter(context: Context?)
    }

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput {
        fun setFilter(filter: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilter)
    }

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router {
        fun moveToFilterDetail(filterType: Int)
    }

}