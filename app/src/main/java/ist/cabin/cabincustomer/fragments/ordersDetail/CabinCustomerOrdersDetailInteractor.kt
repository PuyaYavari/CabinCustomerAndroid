package ist.cabin.cabincustomer.fragments.ordersDetail

class CabinCustomerOrdersDetailInteractor(var output: CabinCustomerOrdersDetailContracts.InteractorOutput?) :
    CabinCustomerOrdersDetailContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //endregion
}
