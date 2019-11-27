package com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition

import android.content.Context

object CabinCustomerAddExtraditionContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        fun onBackPressed()
        fun getApplicationContext(): Context
        fun setupERListener()
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        fun saveData()
        fun moveToCongratulationsPage()
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {
        fun saveData()
    }

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router {
        fun moveToCongratulationsPage()
    }

}