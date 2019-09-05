package ist.cabin.cabinCustomerExtraditions.fragments.congratulations

class CabinCustomerExtraditionsCongratulationsInteractor(var output: CabinCustomerExtraditionsCongratulationsContracts.InteractorOutput?) :
    CabinCustomerExtraditionsCongratulationsContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}