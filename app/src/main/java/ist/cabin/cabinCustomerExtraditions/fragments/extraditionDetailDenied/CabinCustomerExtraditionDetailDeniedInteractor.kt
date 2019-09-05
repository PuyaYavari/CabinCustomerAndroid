package ist.cabin.cabinCustomerExtraditions.fragments.extraditionDetailDenied

class CabinCustomerExtraditionDetailDeniedInteractor(var output: CabinCustomerExtraditionDetailDeniedContracts.InteractorOutput?) :
    CabinCustomerExtraditionDetailDeniedContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}