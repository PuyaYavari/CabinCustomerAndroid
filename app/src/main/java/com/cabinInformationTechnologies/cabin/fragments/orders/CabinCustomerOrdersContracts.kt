package com.cabinInformationTechnologies.cabin.fragments.orders

import android.content.Context
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELOrder
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELOrders

object CabinCustomerOrdersContracts {

    interface View : BaseContracts.View {
        fun setupPage()
        fun showProgressBar()
        fun hideProgressBar()
        fun showNoInternet()
        fun hideNoInternet()
    }

    interface FragmentsView : BaseContracts.View {
        fun moveToOrderDetail(order: MODELOrder)
    }

    interface FragmentsManager {
        val orders: MODELOrders
        var currentPage: Int

        fun getNewPage(page: Int, adapter: CabinCustomerOrdersAdapter)
        fun getFirstPage(context: Context)
        fun refresh(context: Context, refreshLayout: SwipeRefreshLayout?, adapter: CabinCustomerOrdersAdapter)
    }

    interface Presenter : BaseContracts.Presenter

    interface Interactor : BaseContracts.Interactor {
        fun getNewPageIn(context: Context, page: Int, adapter: CabinCustomerOrdersAdapter)
        fun getFirstPage(context: Context)
        fun refresh(context: Context, refreshLayout: SwipeRefreshLayout?, adapter: CabinCustomerOrdersAdapter)
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        fun setFirstPage(orders: MODELOrders)
        fun setOrdersIn(orders: MODELOrders, adapter: CabinCustomerOrdersAdapter)
        fun refresh(orders: MODELOrders)
        fun showNoInternet()
    }

    interface Router : BaseContracts.Router
}