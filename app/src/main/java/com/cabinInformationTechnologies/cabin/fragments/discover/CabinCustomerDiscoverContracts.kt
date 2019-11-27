package com.cabinInformationTechnologies.cabin.fragments.discover

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSort
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSorts

object CabinCustomerDiscoverContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        fun hideHeaderAndNavbar()
        fun showHeaderAndNavbar()
        fun moveToProductDetail(product: MODELProduct, position: Int)
        fun addData(products: List<MODELProduct>?)
        fun updateProduct(product: MODELProduct, position: Int)
        fun showProgressBar()
        fun hideProgressBar()
        fun getCurrentItemCount(): Int
        fun showNoInternet()
        fun hideNoInternet()
        fun unsetFilterButton()
        fun setFilterButton()
        fun showSorts(sorts: MODELSorts)
        fun resetPage()
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        val myDataset: MutableList<MODELProduct>

        fun moveToProductDetail(product: MODELProduct, position: Int)
        fun getProducts(context: Context?, page:Int)
        fun resetPage(context: Context?)
        fun updateLastEnteredProduct(context: Context?)
        fun moveToFilter()
        fun getSortOptions(context: Context?)
        fun setSort(sort: MODELSort?)
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {
        fun getProducts(context: Context, page:Int)
        fun getProducts(context: Context, page:Int, sort:Int)
        fun getProduct(context: Context, id: Int)
        fun getSortOptions(context: Context)
    }

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput {
        fun addData(products: List<MODELProduct>?)
        fun updateProduct(product: MODELProduct)
        fun noInternet(isNetworkConnected: Boolean)
        fun showSorts(sorts: MODELSorts)
    }

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router {
        fun moveToProductDetail(product: MODELProduct)
        fun moveToFilter()
    }

}