package com.cabinInformationTechnologies.cabin.fragments.filter

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilter

object CabinCustomerFilterContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        fun showProgressBar()
        fun hideProgressBar()
        fun setFilter(filter: MODELFilter)
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        fun getFilter(context: Context, filter: MODELFilter?)
        fun moveToFilterDetail(filterType: Int)
        fun getSelectedCount(filter: MODELFilter, filterType: Int): Int
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {
        fun getFilter(context: Context, filter: MODELFilter?)
    }

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput {
        fun setFilter(filter: MODELFilter)
    }

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router {
        fun moveToFilterDetail(filterType: Int)
    }

}