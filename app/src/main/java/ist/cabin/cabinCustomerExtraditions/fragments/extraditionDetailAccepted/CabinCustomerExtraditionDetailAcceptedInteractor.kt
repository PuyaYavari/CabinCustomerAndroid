package ist.cabin.cabinCustomerExtraditions.fragments.extraditionDetailAccepted

class CabinCustomerExtraditionDetailAcceptedInteractor(var output: CabinCustomerExtraditionDetailAcceptedContracts.InteractorOutput?) :
    CabinCustomerExtraditionDetailAcceptedContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}