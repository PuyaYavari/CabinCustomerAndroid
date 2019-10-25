package com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList

class CabinCustomerExtraditionsListInteractor(var output: com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.CabinCustomerExtraditionsListContracts.InteractorOutput?) :
    com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.CabinCustomerExtraditionsListContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun getInitialData(): Unit? { //TODO: RETURN DATA FROM BACKEND
        return null
    }

    //endregion
}