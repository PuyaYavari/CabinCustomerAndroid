package com.cabinInformationTechnologies.cabin.fragments.productDetail

import android.content.Context

object CabinCustomerProductDetailContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        fun setupProductDetail(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct)
        fun showSizesOfColor(id: Int)
        fun populateImagesList()
        fun addToCart(
                productId: Int,
                amount: Int,
                colorId: Int,
                sizeId: Int
            )
        fun setSelectedColor(color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor)
        fun setSelectedSize(size: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize?)
        fun setupColors(colorsDataset : MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor>)
        fun setupSizes(sizesDataset: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize>, firstColorID: Int)
        fun showMessage(message: String, isSuccessful: Boolean)
        fun showDefaultMessage()
        fun setFavoriteButtonTo(color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor)
        fun checkFavorite()
        fun uncheckFavorite()
        fun setTickOnColor(color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor)
        fun showSelectSizeFor(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct, color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor, callback: com.cabinInformationTechnologies.cabin.MainContracts.SelectSizeCallback)
        fun indicateSelectedSize(size: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize)
        fun showProgressBar()
        fun hideProgressBar()
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        fun setInitialColor(color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor?)
        fun setProduct(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct)
        fun addToCart(context: Context,
                      productId: Int,
                      amount: Int,
                      colorId: Int,
                      sizeId: Int
        )
        fun addToCartButtonListener(context: Context)
        fun setupDatasets()
        fun setSelectedColor(color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor)
        fun setSelectedSize(size: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize?)
        fun setSizesDataset(sizesDataset: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize>)
        fun getSizesOfColor(id: Int): MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize>?
        fun requestProduct(context: Context, id: Int)
        fun addToFavorite(context: Context, product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct, color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor)
        fun removeFromFavorite(context: Context, product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct, color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor)
        fun setupFavoriteButton(isFavorite: Boolean)
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {
        fun addToCart(context: Context,
                      productId: Int,
                      amount: Int,
                      colorId: Int,
                      sizeId: Int
        )
        fun requestProduct(context: Context, id: Int)
        fun addFavorite(context: Context, product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct, color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor)
        fun removeFavorite(context: Context, product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct, color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor)
    }

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput {
        fun showMessage(message: String?)
        fun updateProduct(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct)
    }

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router

    interface SizeAdapter {
        fun setDataset(sizes: List<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize>)
    }

}