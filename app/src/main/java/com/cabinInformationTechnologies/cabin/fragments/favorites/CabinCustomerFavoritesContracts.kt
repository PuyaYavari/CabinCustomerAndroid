package com.cabinInformationTechnologies.cabin.fragments.favorites

import android.content.Context

object CabinCustomerFavoritesContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        fun showData(products: List<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct>)
        fun moveToProductDetail(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct)
        fun removeFromFavorites(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct)
        fun undoRemove()
        fun addToCart(
            amount: Int,
            productId: Int,
            color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor,
            size: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize
        )
        fun renewData()
        fun showSelectSize(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct, color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor, callback: com.cabinInformationTechnologies.cabin.MainContracts.SelectSizeCallback)
        fun showProgressBar()
        fun hideProgressBar()
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        fun getFavorites(context: Context, page: Int)
        fun moveToProductDetail(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct)
        fun removeFromFavorites(context: Context, product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct)
        fun addToCart(
            context: Context,
            amount: Int,
            productId: Int,
            color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor,
            size: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize
        )
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {
        fun getFavorites(context: Context, page: Int)
        fun removeFromFavorites(context: Context, product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct)
        fun addToCart(
            context: Context,
            amount: Int,
            productId: Int,
            color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor,
            size: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize
        )
    }

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput {
        fun setData(products: List<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct?>)
        fun undoRemove()
    }

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router {
        fun moveToProductDetail(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct)
    }

}