package ist.cabin.cabincustomer.fragments.orders.fragments.pending

class CabinCustomerOrdersPendingFragmentInteractor(var output: CabinCustomerOrdersPendingFragmentContracts.InteractorOutput?) :
    CabinCustomerOrdersPendingFragmentContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}