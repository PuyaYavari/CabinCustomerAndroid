package ist.cabin.cabinCustomerCart

class CabinCustomerCartInteractor(var output: CabinCustomerCartContracts.InteractorOutput?) :
    CabinCustomerCartContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}