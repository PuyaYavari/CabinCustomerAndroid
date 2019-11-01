package com.cabinInformationTechnologies.cabin.fragments.favorites

import android.content.Context
import com.cabinInformationTechnologies.cabin.MainContracts
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize

object CabinCustomerFavoritesContracts {

    interface View : BaseContracts.View {
        fun showData(products: List<MODELProduct>)
        fun moveToProductDetail(product: MODELProduct)
        fun removeFromFavorites(product: MODELProduct)
        fun undoRemove()
        fun addToCart(
            amount: Int,
            productId: Int,
            color: MODELColor,
            size: MODELSize
        )
        fun renewData()
        fun showSelectSize(product: MODELProduct, color: MODELColor, callback: MainContracts.SelectSizeCallback)
        fun showProgressBar()
        fun hideProgressBar()
        fun getCurrentItemCount(): Int
        fun feedback(message: String)
        fun showNoInternet()
        fun hideNoInternet()
    }

    interface Presenter : BaseContracts.Presenter {
        fun getFavorites(context: Context, page: Int)
        fun moveToProductDetail(product: MODELProduct)
        fun removeFromFavorites(context: Context, product: MODELProduct)
        fun addToCart(
            context: Context,
            amount: Int,
            productId: Int,
            color: MODELColor,
            size: MODELSize
        )
    }

    interface Interactor : BaseContracts.Interactor {
        fun getFavorites(context: Context, page: Int)
        fun removeFromFavorites(context: Context, product: MODELProduct)
        fun addToCart(
            context: Context,
            amount: Int,
            productId: Int,
            color: MODELColor,
            size: MODELSize
        )
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        fun setData(products: List<MODELProduct?>)
        fun undoRemove()
        fun feedback(message: String?)
        fun noInternet(isNetworkConnected: Boolean)
    }

    interface Router : BaseContracts.Router {
        fun moveToProductDetail(product: MODELProduct)
    }

}