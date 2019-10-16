package ist.cabin.cabincustomer.fragments.filter

import android.content.Context
import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerFilterContracts {

    interface View : BaseContracts.View {
        //TODO
    }

    interface Presenter : BaseContracts.Presenter {
        fun getFilter(context: Context?)
        fun moveToFilterDetail()
    }

    interface Interactor : BaseContracts.Interactor {
        fun getFilter(context: Context?)
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        fun setFilter()//TODO
    }

    interface Router : BaseContracts.Router {
//        fun moveToFilterDetail(filterDetailGenerator: MainContracts.FilterDetailGenerator)
    }

}