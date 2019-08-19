package ist.cabin.cabincustomer.fragments.ordersDetail

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerOrdersDetailContracts {

    interface View : BaseContracts.View {
        fun setupPendingPage()
        fun setupShippingPage()
        fun setupSentPage()
    }

    interface Presenter : BaseContracts.Presenter {
        fun setupPropperPage(pageID: Int)
    }

    interface Interactor : BaseContracts.Interactor {
        //TODO
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : BaseContracts.Router {
        //TODO
    }

    interface Detailbox {
        fun getType(): Int
    }

}