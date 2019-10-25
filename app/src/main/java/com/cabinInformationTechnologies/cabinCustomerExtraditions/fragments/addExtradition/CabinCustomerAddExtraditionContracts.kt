package com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition

import android.content.Context

object CabinCustomerAddExtraditionContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        fun onBackPressed()
        fun getApplicationContext(): Context
        fun setupERListener(product: com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.CabinCustomerAddExtraditionContracts.Product)
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

    interface Product : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Product { //FIXME: REMOVE
        var ER: String
        var temp: Int

        fun getProductImage(): Int
    }

}