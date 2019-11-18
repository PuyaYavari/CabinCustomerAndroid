package com.cabinInformationTechnologies.cabin.fragments.orders

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELOrders

object CabinCustomerOrdersContracts {

    interface View : BaseContracts.View {
        fun showProgressBar()
        fun hideProgressBar()
    }

    interface FragmentsView : BaseContracts.View {

    }

    interface FragmentsManager {
        val orders: MODELOrders
        var currentPage: Int

        fun getNewPage(page: Int, adapter: CabinCustomerOrdersAdapter)
    }

    interface Presenter : BaseContracts.Presenter

    interface Interactor : BaseContracts.Interactor {
        fun getNewPageIn(context: Context, page: Int, adapter: CabinCustomerOrdersAdapter)
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        fun setOrdersIn(orders: MODELOrders, adapter: CabinCustomerOrdersAdapter)
    }

    interface Router : BaseContracts.Router
}