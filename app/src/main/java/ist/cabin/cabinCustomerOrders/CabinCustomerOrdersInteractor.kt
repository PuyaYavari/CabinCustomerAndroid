package ist.cabin.cabinCustomerOrders

class CabinCustomerOrdersInteractor(var output: CabinCustomerOrdersContracts.InteractorOutput?) :
    CabinCustomerOrdersContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}