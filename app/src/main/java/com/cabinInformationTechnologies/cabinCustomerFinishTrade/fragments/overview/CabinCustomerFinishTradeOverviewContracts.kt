package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.overview

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAgreements

object CabinCustomerFinishTradeOverviewContracts {

    interface View : BaseContracts.View {
        fun setAgreements(agreements: MODELAgreements)
    }

    interface Presenter : BaseContracts.Presenter {
        fun listAgreements(context: Context?, orderId: Int?)
    }

    interface Interactor : BaseContracts.Interactor {
        fun listAgreements(context: Context, orderId: Int)
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        fun setAgreements(agreements: MODELAgreements)
    }

    interface Router : BaseContracts.Router {
        //TODO
    }

}