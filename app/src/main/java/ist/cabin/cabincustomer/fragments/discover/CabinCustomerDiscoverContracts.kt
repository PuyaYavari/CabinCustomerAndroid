package ist.cabin.cabincustomer.fragments.discover

import android.content.Context
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerBase.models.local.MODELProduct

object CabinCustomerDiscoverContracts {

    interface View : BaseContracts.View {
        fun hideHeaderAndNavbar()
        fun showHeaderAndNavbar()
        fun moveToProductDetail(product: MODELProduct, position: Int)
        fun addData(products: List<MODELProduct>?)
        fun updateProduct(product: MODELProduct, position: Int)
    }

    interface Presenter : BaseContracts.Presenter {
        fun moveToProductDetail(product: MODELProduct, position: Int)
        fun getItemData(page:Int, pageSize:Int)
        fun resetPage()
        fun updateLastEnteredProduct(context: Context)
        fun moveToFilter()
    }

    interface Interactor : BaseContracts.Interactor {
        fun getItemData(context: Context, page:Int, pageSize:Int)
        fun getProduct(context: Context, id: Int)
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        fun addData(products: List<MODELProduct>?)
        fun updateProduct(product: MODELProduct)
    }

    interface Router : BaseContracts.Router {
        fun moveToProductDetail(product: MODELProduct)
        fun moveToFilter()
    }

}