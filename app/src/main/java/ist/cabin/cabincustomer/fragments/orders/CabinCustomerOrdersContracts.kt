package ist.cabin.cabincustomer.fragments.orders

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerOrdersContracts {

    interface View : BaseContracts.View

    interface FragmentsView : BaseContracts.View {
        fun orderboxOnClickListener()
    }

    interface Presenter : BaseContracts.Presenter {

    }

    interface Interactor : BaseContracts.Interactor

    interface InteractorOutput : BaseContracts.InteractorOutput

    interface Router : BaseContracts.Router {
    }

}