package ist.cabin.cabincustomer.fragments.filter

import android.content.Context
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerBase.models.local.MODELFilter

object CabinCustomerFilterContracts {

    interface View : BaseContracts.View {
        fun showProgressBar()
        fun hideProgressBar()
        fun setFilter(filter: MODELFilter)
    }

    interface Presenter : BaseContracts.Presenter {
        fun getFilter(context: Context?)
        fun moveToFilterDetail(filterType: Int)
    }

    interface Interactor : BaseContracts.Interactor {
        fun getFilter(context: Context?)
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        fun setFilter(filter: MODELFilter)
    }

    interface Router : BaseContracts.Router {
        fun moveToFilterDetail(filterType: Int)
    }

}