package ist.cabin.cabincustomer.fragments.cart

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerCartContracts {

    interface View : BaseContracts.View {
        fun showPriceDetail()
        fun hidePriceDetail()
    }

    interface Presenter : BaseContracts.Presenter {
        fun moveToFinishTrade()
        fun togglePriceDetail()
    }

    interface Interactor : BaseContracts.Interactor {
        //TODO
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : BaseContracts.Router {
        fun moveToFinishTrade()
    }

    interface Product {
        var count: Int

        fun getID(): String
    }

}