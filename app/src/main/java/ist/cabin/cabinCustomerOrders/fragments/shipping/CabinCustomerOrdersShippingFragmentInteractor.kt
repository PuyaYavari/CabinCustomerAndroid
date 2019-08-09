package ist.cabin.cabinCustomerOrders.fragments.shipping

class CabinCustomerOrdersShippingFragmentInteractor(var output: CabinCustomerOrdersShippingFragmentContracts.InteractorOutput?) :
    CabinCustomerOrdersShippingFragmentContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}