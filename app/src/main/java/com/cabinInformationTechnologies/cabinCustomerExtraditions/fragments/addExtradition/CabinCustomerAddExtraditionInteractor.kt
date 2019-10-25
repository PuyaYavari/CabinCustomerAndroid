package com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition

import android.util.Log

class CabinCustomerAddExtraditionInteractor(var output: com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.CabinCustomerAddExtraditionContracts.InteractorOutput?) :
    com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.CabinCustomerAddExtraditionContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun saveData() {
        Log.d("Add Extradition", "Save Data")
        //TODO: SEND DATA TO BACKEND
    }

    //endregion
}