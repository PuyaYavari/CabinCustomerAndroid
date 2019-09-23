package ist.cabin.cabincustomer.fragments.productDetail

import android.content.Context
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerBase.models.local.MODELColor
import ist.cabin.cabinCustomerBase.models.local.MODELSize

object CabinCustomerProductDetailContracts {

    interface View : BaseContracts.View {
        fun showMeasuresOfColor(id: Int)
        fun populateImagesList()
        fun addToCart(amount: Int,
                      productId: Int,
                      color: MODELColor,
                      size: MODELSize)
        fun setSelectedColor(color: MODELColor)
        fun setSelectedSize(size: MODELSize?)
    }

    interface Presenter : BaseContracts.Presenter {
        fun addToCart(
            context: Context,
            amount: Int,
            productId: Int,
            color: MODELColor,
            size: MODELSize)
    }

    interface Interactor : BaseContracts.Interactor {
        fun addToCart(
            context: Context,
            amount: Int,
            productId: Int,
            color: MODELColor,
            size: MODELSize
        )
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : BaseContracts.Router {
        //TODO
    }

    interface SizeAdapter {
        fun setDataset(sizes: List<MODELSize>)
    }

}