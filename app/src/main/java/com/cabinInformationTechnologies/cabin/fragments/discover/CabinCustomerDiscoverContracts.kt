package com.cabinInformationTechnologies.cabin.fragments.discover

import android.content.Context

object CabinCustomerDiscoverContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        fun hideHeaderAndNavbar()
        fun showHeaderAndNavbar()
        fun moveToProductDetail(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct, position: Int)
        fun addData(products: List<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct>?)
        fun updateProduct(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct, position: Int)
        fun showProgressBar()
        fun hideProgressBar()
        fun getCurrentItemCount(): Int
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        fun moveToProductDetail(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct, position: Int)
        fun getProducts(page:Int, pageSize:Int)
        fun resetPage()
        fun updateLastEnteredProduct(context: Context)
        fun moveToFilter()
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {
        fun getProducts(context: Context, page:Int, pageSize:Int)
        fun getProduct(context: Context, id: Int)
    }

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput {
        fun addData(products: List<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct>?)
        fun updateProduct(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct)
    }

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router {
        fun moveToProductDetail(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct)
        fun moveToFilter()
    }

}