package ist.cabin.cabinCustomerEmailconfirmation

class CabinCustomerEmailconfirmationInteractor(var output: CabinCustomerEmailconfirmationContracts.InteractorOutput?) :
    CabinCustomerEmailconfirmationContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}