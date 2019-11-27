package com.cabinInformationTechnologies.cabin.fragments.cart

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELCart
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct

object CabinCustomerCartContracts {

    interface View : BaseContracts.View {
        fun showPriceDetail()
        fun hidePriceDetail()
        fun setData(cart: MODELCart)
        fun updateProduct(product: MODELProduct)
        fun clearAll()
        fun addShippingPrice(sellerName: String, price: Double)
        fun clearCargoPrices()
        fun moveToProductDetail(product: MODELProduct, color: MODELColor)
        fun showProgressBar()
        fun hideProgressBar()
        fun getCurrentItemCount(): Int
        fun feedback(message: String)
        fun showNoInternet()
        fun hideNoInternet()
        fun setActivityCart(cart: MODELCart)
    }

    interface Presenter : BaseContracts.Presenter {
        val myDataset: MutableList<MODELProduct>

        fun togglePriceDetail()
        fun getCart(context: Context)
        fun updateProduct(context: Context, product: MODELProduct)
        fun areProductsEqual(first: MODELProduct, second: MODELProduct): Boolean
        fun moveToProductDetail(product: MODELProduct, color: MODELColor)
    }

    interface Interactor : BaseContracts.Interactor {
        fun getCart(context: Context)
        fun updateProduct(context: Context, product: MODELProduct)
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        fun setCart(cart: MODELCart?)
        fun noInternet(isNetworkConnected: Boolean)
    }

    interface Router : BaseContracts.Router {
        fun moveToProductDetail(product: MODELProduct, color: MODELColor)
    }

    interface CartCallback {
        fun setSellerId(id: Int?)
        fun setProductId(id: Int?)
        fun setColorId(id: Int?)
        fun feedback(message: String)
        fun removeItems()
    }

    interface ViewForInteractor {
        fun getFragmentContext(): Context?
    }
}