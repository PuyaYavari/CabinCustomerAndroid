package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.mainMenu

class CabinCustomerProfileOptionsMainMenuInteractor(var output: com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.mainMenu.CabinCustomerProfileOptionsMainMenuContracts.InteractorOutput?) :
    com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.mainMenu.CabinCustomerProfileOptionsMainMenuContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //endregion
}