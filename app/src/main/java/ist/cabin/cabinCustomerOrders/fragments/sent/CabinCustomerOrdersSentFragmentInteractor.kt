package ist.cabin.cabinCustomerOrders.fragments.sent

class CabinCustomerOrdersSentFragmentInteractor(var output: CabinCustomerOrdersSentFragmentContracts.InteractorOutput?) :
    CabinCustomerOrdersSentFragmentContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}