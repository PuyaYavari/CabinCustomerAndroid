package ist.cabin.cabinCustomerFinishTrade.fragments.main

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerFinishTradeMainContracts {

    interface View : BaseContracts.View {
        fun showPriceDetail()
        fun hidePriceDetail()
        fun pageForward()
        fun setPage(page: Int)
        fun getPage(): Int
        fun moveOut()
        fun setupFirstPage()
        fun setupSecondPage()
        fun setupLastPage()
        fun moveIndicatorTo(fromId: Int, toId: Int)
    }

    interface Presenter : BaseContracts.Presenter {
        fun togglePriceDetail()
        fun pageForward(currentPosition: Int)
        fun pageBackward(currentPosition: Int)
        fun pageBackToFirstPage()
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