package ist.cabin.cabincustomer.fragments.cart

import android.content.Context
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerBase.models.local.MODELCart
import ist.cabin.cabinCustomerBase.models.local.MODELProduct

object CabinCustomerCartContracts {

    interface View : BaseContracts.View {
        val myDataset: MutableList<MODELProduct>

        fun showPriceDetail()
        fun hidePriceDetail()
        fun setData(cart: MODELCart)
        fun updateProduct(product: MODELProduct)
        fun clearAll()
        fun addShippingPrice(sellerName: String, price: Int)
        fun clearCargoPrices()
    }

    interface Presenter : BaseContracts.Presenter {
        fun moveToFinishTrade()
        fun togglePriceDetail()
        fun getCart(context: Context)
        fun updateProduct(context: Context, product: MODELProduct)
        fun areProductsEqual(first: MODELProduct, second: MODELProduct): Boolean
    }

    interface Interactor : BaseContracts.Interactor {
        fun getCart(context: Context)
        fun updateProduct(context: Context, product: MODELProduct)
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        fun setCart(cart: MODELCart?)
    }

    interface Router : BaseContracts.Router {
        fun moveToFinishTrade()
    }
}