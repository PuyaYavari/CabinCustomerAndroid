package com.cabinInformationTechnologies.cabin.fragments.productDetail

import android.content.Context
import androidx.navigation.NavController
import com.cabinInformationTechnologies.cabin.MainContracts
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.*

object CabinCustomerProductDetailContracts {

    interface View : BaseContracts.View {
        fun setupProductDetail(product: MODELProduct)
        fun setupPrice(price: Double, priceUnit: String)
        fun setupPrice(price: Double, discountedPrice: Double, priceUnit: String)
        fun showSizesOfColor(id: Int)
        fun populateImagesList()
        fun addToCart(
                productId: Int,
                amount: Int,
                colorId: Int,
                sizeId: Int
            )
        fun setSelectedColor(color: MODELColor)
        fun setSelectedSize(size: MODELSize?)
        fun setupColors(colorsDataset : MutableList<MODELColor>)
        fun setupImages(imageList: ArrayList<MODELImage>)
        fun setupSizes(sizesDataset: MutableList<MODELSize>, firstColorID: Int)
        fun showDefaultMessage()
        fun setFavoriteButtonTo(color: MODELColor)
        fun checkFavorite()
        fun uncheckFavorite()
        fun setTickOnColor(color: MODELColor)
        fun showSelectSizeFor(product: MODELProduct, color: MODELColor, callback: MainContracts.SelectSizeCallback)
        fun indicateSelectedSize(size: MODELSize)
        fun showProgressBar()
        fun hideProgressBar()
        fun directToRegistration()
        fun showButtonProgressBar()
        fun showCounter(amount: Int)
        fun showAddToCartButton()
        fun setActivityCart(cart: MODELCart)
        fun setAmount(amount: Int)
        fun closePage()
    }

    interface Presenter : BaseContracts.Presenter {
        fun setInitialColor(color: MODELColor?)
        fun setProduct(product: MODELProduct)
        fun addToCart(context: Context,
                      productId: Int,
                      amount: Int,
                      colorId: Int,
                      sizeId: Int
        )
        fun addToCartButtonListener(context: Context)
        fun setupDatasets()
        fun setSelectedColor(color: MODELColor)
        fun setSelectedSize(size: MODELSize?)
        fun setSizesDataset(sizesDataset: MutableList<MODELSize>)
        fun getSizesOfColor(id: Int): MutableList<MODELSize>?
        fun requestProduct(context: Context?, id: Int, navController: NavController)
        fun addToFavorite(context: Context?, product: MODELProduct, color: MODELColor)
        fun removeFromFavorite(context: Context?, product: MODELProduct, color: MODELColor)
        fun setupFavoriteButton(isFavorite: Boolean)
        fun increaseAmount(context: Context?)
        fun decreaseAmount(context: Context?)
        fun setCart(cart: MODELCart?)
    }

    interface Interactor : BaseContracts.Interactor {
        fun addToCart(context: Context,
                      productId: Int,
                      amount: Int,
                      colorId: Int,
                      sizeId: Int
        )
        fun requestProduct(context: Context, id: Int, navController: NavController)
        fun addFavorite(context: Context, product: MODELProduct, color: MODELColor)
        fun removeFavorite(context: Context, product: MODELProduct, color: MODELColor)
        fun updateProduct(context: Context, productId: Int, colorId: Int, sizeId: Int, amount: Int)
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        fun showSuccessMessage()
        fun showButton()
        fun updateProduct(product: MODELProduct)
        fun setCart(cart: MODELCart?)
        fun productAddedToCart()
    }

    interface Router : BaseContracts.Router

    interface SizeAdapter {
        fun setDataset(sizes: List<MODELSize>)
    }

}