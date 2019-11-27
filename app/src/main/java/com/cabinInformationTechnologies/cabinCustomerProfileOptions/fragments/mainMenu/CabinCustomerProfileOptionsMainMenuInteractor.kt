package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.mainMenu

class CabinCustomerProfileOptionsMainMenuInteractor(var output: CabinCustomerProfileOptionsMainMenuContracts.InteractorOutput?) :
    CabinCustomerProfileOptionsMainMenuContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //endregion
}