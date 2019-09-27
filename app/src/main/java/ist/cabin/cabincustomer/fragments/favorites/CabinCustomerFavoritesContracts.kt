package ist.cabin.cabincustomer.fragments.favorites

import android.content.Context
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerBase.models.local.MODELColor
import ist.cabin.cabinCustomerBase.models.local.MODELProduct
import ist.cabin.cabinCustomerBase.models.local.MODELSize

object CabinCustomerFavoritesContracts {

    interface View : BaseContracts.View {
        fun moveToProductDetail(product: MODELProduct)
        fun addToCart(
            context: Context,
            amount: Int,
            productId: Int,
            color: MODELColor,
            size: MODELSize
        )
    }

    interface Presenter : BaseContracts.Presenter {
        fun moveToProductDetail(product: MODELProduct)
        fun addToCart(
            context: Context,
            amount: Int,
            productId: Int,
            color: MODELColor,
            size: MODELSize
        )
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
        fun moveToProductDetail(product: MODELProduct)
    }

}