package com.cabinInformationTechnologies.cabin.fragments.ordersDetail

import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELOrder

object CabinCustomerOrdersDetailContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        fun setupPendingPage(myDataset: MutableList<Detailbox>)
        fun setupShippingPage(myDataset: MutableList<Detailbox>)
        fun setupSentPage(myDataset: MutableList<Detailbox>)
        fun showProgressBar()
        fun hideProgressBar()
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        fun setupProperPage(pageID: Int, order: MODELOrder)
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router

    interface Detailbox {
        fun getType(): Int
    }

}