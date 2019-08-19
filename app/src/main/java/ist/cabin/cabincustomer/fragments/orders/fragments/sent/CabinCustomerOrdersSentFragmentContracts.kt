package ist.cabin.cabincustomer.fragments.orders.fragments.sent

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerOrdersSentFragmentContracts {

    interface View : BaseContracts.View {
        //TODO
    }

    interface Presenter : BaseContracts.Presenter {
        fun showOrderDetails()
    }

    interface Interactor : BaseContracts.Interactor {
        //TODO
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : BaseContracts.Router {
        fun moveToDetailsPage()
    }

}