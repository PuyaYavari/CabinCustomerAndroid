package ist.cabin.cabinCustomerExtraditions.fragments.extraditionDetail

class CabinCustomerExtraditionDetailInteractor(var output: CabinCustomerExtraditionDetailContracts.InteractorOutput?) :
    CabinCustomerExtraditionDetailContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}