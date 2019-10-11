package ist.cabin.cabincustomer.fragments.productDetail

import android.content.Context
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerBase.models.local.MODELColor
import ist.cabin.cabinCustomerBase.models.local.MODELProduct
import ist.cabin.cabinCustomerBase.models.local.MODELSize

object CabinCustomerProductDetailContracts {

    interface View : BaseContracts.View {
        fun setupProductDetail(product: MODELProduct)
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
        fun setupSizes(sizesDataset: MutableList<MODELSize>, firstColorID: Int)
        fun showMessage(message: String, isSuccessful: Boolean)
        fun showDefaultMessage()
        fun setFavoriteButtonTo(color: MODELColor)
        fun checkFavorite()
        fun uncheckFavorite()
        fun setTickOnColor(color: MODELColor)
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
        fun addToCartButtonListener()
        fun setupDatasets()
        fun setSelectedColor(color: MODELColor)
        fun setSelectedSize(size: MODELSize?)
        fun setSizesDataset(sizesDataset: MutableList<MODELSize>)
        fun getSizesOfColor(id: Int): MutableList<MODELSize>?
        fun requestProduct(context: Context, id: Int)
        fun addToFavorite(context: Context, product: MODELProduct, color: MODELColor)
        fun removeFromFavorite(context: Context, product: MODELProduct, color: MODELColor)
        fun setupFavoriteButton(isFavorite: Boolean)
    }

    interface Interactor : BaseContracts.Interactor {
        fun addToCart(context: Context,
                      productId: Int,
                      amount: Int,
                      colorId: Int,
                      sizeId: Int
        )
        fun requestProduct(context: Context, id: Int)
        fun addFavorite(context: Context, product: MODELProduct, color: MODELColor)
        fun removeFavorite(context: Context, product: MODELProduct, color: MODELColor)
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        fun showMessage(message: String?)
        fun updateProduct(product: MODELProduct)
    }

    interface Router : BaseContracts.Router

    interface SizeAdapter {
        fun setDataset(sizes: List<MODELSize>)
    }

}