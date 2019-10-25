package com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtraditionCurrentItemsList

class CabinCustomerAddExtraditionCurrentItemsListInteractor(var output: com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtraditionCurrentItemsList.CabinCustomerAddExtraditionCurrentItemsListContracts.InteractorOutput?) :
    com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtraditionCurrentItemsList.CabinCustomerAddExtraditionCurrentItemsListContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun getProductsList() {
        //TODO: GET DATA FROM BACKEND
    }

    //endregion
}