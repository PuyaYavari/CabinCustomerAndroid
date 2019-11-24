package com.cabinInformationTechnologies.cabin.fragments.home

import com.cabinInformationTechnologies.cabin.fragments.home.adapters.CabinCustomerHomeAdapter
import com.cabinInformationTechnologies.cabin.fragments.home.adapters.CabinCustomerHomeHeaderAdapter
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELBannerGroup
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELHeader
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELHeaders

object CabinCustomerHomeContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        fun showProgressBar()
        fun hideProgressBar()
        fun lockDrawer()
        fun unlockDrawer()
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        val headers: MutableList<MODELHeader>
        var myDataset: MutableList<MODELBannerGroup>
        var headerAdapter: CabinCustomerHomeHeaderAdapter
        var homeAdapter: CabinCustomerHomeAdapter

        fun moveToRegistration()
        //TODO: SEND SELECTED BANNER DATA TO BACKEND AND GO TO DISCOVER
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {
        //TODO: GET HEADER
    }

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput {
        fun setHeaderData(data: MODELHeaders)
    }

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router {
        fun moveToRegistration()
    }

}