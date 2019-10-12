package ist.cabin.cabincustomer.fragments.favorites

import android.content.Context
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerBase.models.local.MODELColor
import ist.cabin.cabinCustomerBase.models.local.MODELProduct
import ist.cabin.cabinCustomerBase.models.local.MODELSize

object CabinCustomerFavoritesContracts {

    interface View : BaseContracts.View {
        fun showData(products: List<MODELProduct>)
        fun moveToProductDetail(product: MODELProduct)
        fun removeFromFavorites(product: MODELProduct)
        fun undoRemove()
        fun addToCart(
            context: Context,
            amount: Int,
            productId: Int,
            color: MODELColor,
            size: MODELSize
        )
        fun renewData()
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
    }

    interface Router : BaseContracts.Router {
        fun moveToProductDetail(product: MODELProduct)
    }

}