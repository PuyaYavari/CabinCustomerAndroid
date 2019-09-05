package ist.cabin.cabinCustomerFinishTrade

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerFinishTradeContracts {

    interface View : BaseContracts.View {
        fun showPriceDetail()
        fun hidePriceDetail()
    }

    interface Presenter : BaseContracts.Presenter {
        fun togglePriceDetail()
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

}