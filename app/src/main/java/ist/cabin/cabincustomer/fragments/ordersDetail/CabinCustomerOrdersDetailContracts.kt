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

    interface Interactor : BaseContracts.Interactor

    interface InteractorOutput : BaseContracts.InteractorOutput

    interface Router : BaseContracts.Router 

    interface Detailbox {
        fun getType(): Int
    }

}