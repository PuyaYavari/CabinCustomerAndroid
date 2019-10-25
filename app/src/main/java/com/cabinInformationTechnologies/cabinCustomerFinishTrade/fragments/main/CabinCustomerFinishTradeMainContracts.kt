package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.main

object CabinCustomerFinishTradeMainContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
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

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        fun togglePriceDetail()
        fun pageForward(currentPosition: Int)
        fun pageBackward(currentPosition: Int)
        fun pageBackToFirstPage()
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {
        //TODO
    }

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router {
        //TODO
    }

}