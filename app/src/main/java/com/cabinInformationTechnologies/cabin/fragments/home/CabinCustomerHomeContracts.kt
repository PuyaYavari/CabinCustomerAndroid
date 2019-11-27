package com.cabinInformationTechnologies.cabin.fragments.home

import android.content.Context
import com.cabinInformationTechnologies.cabin.fragments.home.adapters.CabinCustomerHomeAdapter
import com.cabinInformationTechnologies.cabin.fragments.home.adapters.CabinCustomerHomeHeaderAdapter
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELBannerGroup
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELHeader
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELHeaders

object CabinCustomerHomeContracts {

    interface View : BaseContracts.View {
        fun showProgressBar()
        fun hideProgressBar()
        fun lockDrawer()
        fun unlockDrawer()
    }

    interface Presenter : BaseContracts.Presenter {
        val headers: MutableList<MODELHeader>
        var myDataset: MutableList<MODELBannerGroup>
        var headerAdapter: CabinCustomerHomeHeaderAdapter
        var homeAdapter: CabinCustomerHomeAdapter

        fun moveToRegistration()
        fun requestHeaders(context: Context?)
    }

    interface Interactor : BaseContracts.Interactor {
        fun getHeaders(context: Context)
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        fun setHeaderData(data: MODELHeaders)
    }

    interface Router : BaseContracts.Router {
        fun moveToRegistration()
    }

}