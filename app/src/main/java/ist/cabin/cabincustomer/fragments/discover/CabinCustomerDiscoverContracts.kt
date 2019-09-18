package ist.cabin.cabincustomer.fragments.discover

import android.content.Context
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerBase.models.local.MODELProduct

object CabinCustomerDiscoverContracts {

    interface View : BaseContracts.View {
        fun hideHeaderAndNavbar()
        fun showHeaderAndNavbar()
        fun moveToProductDetail(product: MODELProduct)
        fun addData(products: List<MODELProduct>?)
    }

    interface Presenter : BaseContracts.Presenter {
        fun moveToProductDetail(product: MODELProduct)
        fun getItemData(page:Int, pageSize:Int)
        fun resetPage()
    }

    interface Interactor : BaseContracts.Interactor {
        fun getItemData(context: Context, page:Int, pageSize:Int)
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        fun addData(products: List<MODELProduct>?)
    }

    interface Router : BaseContracts.Router {
        fun moveToProductDetail(product: MODELProduct)
    }

}