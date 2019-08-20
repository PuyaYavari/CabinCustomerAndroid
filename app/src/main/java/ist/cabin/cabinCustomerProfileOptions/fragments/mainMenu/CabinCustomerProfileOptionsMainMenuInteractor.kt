package ist.cabin.cabinCustomerProfileOptions.fragments.mainMenu

class CabinCustomerProfileOptionsMainMenuInteractor(var output: CabinCustomerProfileOptionsMainMenuContracts.InteractorOutput?) :
    CabinCustomerProfileOptionsMainMenuContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}