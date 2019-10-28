package com.cabinInformationTechnologies.cabin.fragments.cart

import android.content.Context

object CabinCustomerCartContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        val myDataset: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct>

        fun showPriceDetail()
        fun hidePriceDetail()
        fun setData(cart: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELCart)
        fun updateProduct(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct)
        fun clearAll()
        fun addShippingPrice(sellerName: String, price: Double)
        fun clearCargoPrices()
        fun moveToProductDetail(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct, color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor)
        fun showProgressBar()
        fun hideProgressBar()
        fun getCurrentItemCount(): Int
        fun feedback(message: String)
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        fun moveToFinishTrade()
        fun togglePriceDetail()
        fun getCart(context: Context)
        fun updateProduct(context: Context, product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct)
        fun areProductsEqual(first: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct, second: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct): Boolean
        fun moveToProductDetail(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct, color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor)
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {
        fun getCart(context: Context)
        fun updateProduct(context: Context, product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct)
    }

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput {
        fun setCart(cart: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELCart?)
        fun feedback(message: String)
    }

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router {
        fun moveToFinishTrade()
        fun moveToProductDetail(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct, color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor)
    }

    interface CartCallback {
        fun setSellerId(id: Int?)
        fun setProductId(id: Int?)
        fun setColorId(id: Int?)
        fun feedback(message: String)
        fun removeItems()
    }
}