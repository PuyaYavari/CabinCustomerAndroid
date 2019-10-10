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
        fun showMessage(message: String?)
    }

    interface Presenter : BaseContracts.Presenter {
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
    }

    interface Interactor : BaseContracts.Interactor {
        fun addToCart(context: Context,
                      productId: Int,
                      amount: Int,
                      colorId: Int,
                      sizeId: Int
        )
        fun requestProduct(context: Context, id: Int)
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