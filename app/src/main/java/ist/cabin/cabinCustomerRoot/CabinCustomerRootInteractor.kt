package ist.cabin.cabinCustomerRoot

class CabinCustomerRootInteractor(var output: CabinCustomerRootContracts.InteractorOutput?) :
    CabinCustomerRootContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //endregion
}