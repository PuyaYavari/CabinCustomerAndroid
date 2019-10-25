package com.cabinInformationTechnologies.cabin.fragments.ordersDetail

object CabinCustomerOrdersDetailContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        fun setupPendingPage()
        fun setupShippingPage()
        fun setupSentPage()
        fun showProgressBar()
        fun hideProgressBar()
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        fun setupPropperPage(pageID: Int)
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router

    interface Detailbox {
        fun getType(): Int
    }

}